from .exceptions import PasswordMismatchException

def validate_password(password, confirm_password):
    if password != confirm_password:
        raise PasswordMismatchException('Passwords do not match')