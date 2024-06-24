from django.shortcuts import render,redirect
from django.contrib.auth.decorators import login_required
from django.http import HttpResponse
from .models import Task
# Create your views here.


@login_required
def task_list(request):
    tasks= Task.objects.filter(user_id=request.user.user_id)
    return render(request, 'tasks/task_list.html', {'tasks': tasks})

@login_required
def task_detail(request, task_id):
    task = Task.objects.get(pk=task_id)    
        
    if request.method == "DELETE":
        task.delete()
        return HttpResponse(status=204) 
        
    return render(request, 'tasks/task_detail.html',{'task': task})

@login_required
def add_task(request):
    if request.method == 'POST':
        title = request.POST['title']
        description = request.POST['description']
        completed = 'completed' in request.POST
        
        Task(title=title, description= description, completed=completed, user_id=request.user.user_id).save()
        
        return redirect('task_list')     
    return render(request, 'tasks/add_task.html',{'button_text': 'Add Task'})

@login_required
def update_task(request,task_id):
    task = Task.objects.get(pk=task_id)
    if request.method == 'POST':
        title = request.POST['title']
        description = request.POST['description']
        completed = 'completed' in request.POST
        
        task.title = title
        task.description = description
        task.completed = completed
        
        task.save()
        return redirect(f'/tasks/{task.task_id}')
    
    return render(request, 'tasks/add_task.html', {'task': task,'button_text': 'Update Task'})