from django.shortcuts import render,redirect
from django.contrib.auth import login,authenticate,logout
from django.db import IntegrityError
from .models import User
from .service import validate_password
from .exceptions import PasswordMismatchException


def signup(request):
    if request.method == 'POST':
        email = request.POST['email']
        password = request.POST['password']
        confirm_password = request.POST['confirm_password']
        try:
            validate_password(password, confirm_password)
            user = User(email=email)
            user.set_password(password)
            user.save()
            login(request, user)
        except IntegrityError as integrity_error:
            return render(request, 'accounts/signup.html', {'error': 'Email already exists!'})    
        except PasswordMismatchException as e:
            return render(request, 'accounts/signup.html', {'error': str(e)})    
        return redirect('/account/login')
    
    return render(request, 'accounts/signup.html')


def login_view(request):
    if request.method == 'POST':
        email = request.POST['username']
        password = request.POST['password']
        user = authenticate(request, email=email, password=password)
        if user is not None:
            login(request, user)
            return redirect('/tasks')
        
        return render(request, 'accounts/login.html', {'error': 'Invalid email or password'})
        
    return render(request, 'accounts/login.html')    


def logout_view(request):
    logout(request)
    request.session.flush()
    return redirect('login')  


"""
 try:
            user = User.objects.get(email=email)
        except User.DoesNotExist:
            return render(request, 'accounts/login.html', {'error': 'Invalid email or password'})
            
        if user.check_password(password):
            request.session['user_id'] = user.user_id
            request.session['username'] = user.email.split('@')[0]
            return redirect('/tasks')

"""