from django.contrib import admin
from .models import Task

# Register your models here.
class TaskAdmin(admin.ModelAdmin):
    list_display = ('title','description','completed','created','user')
    list_filter = ('completed','created')
    search_fields = ('title','description')
    ordering = ('completed','created')
    
admin.site.register(Task, TaskAdmin)
