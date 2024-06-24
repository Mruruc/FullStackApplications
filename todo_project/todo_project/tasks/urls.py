from django.urls import path
from .views import *


urlpatterns = [
    path('', task_list, name='task_list'),
    path('add-task/', add_task, name='add_task'),
    path('update-task/<int:task_id>/', update_task, name='update_task'),
    path('<int:task_id>/', task_detail,name='task_detail'),
]






