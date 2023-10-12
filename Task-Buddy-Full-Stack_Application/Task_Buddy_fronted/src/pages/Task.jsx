import React from 'react'
import { useParams } from 'react-router-dom';
import Form from '../../Component/main/tasks/Form';
import TaskList from '../../Component/main/tasks/TaskList';
import SideBar from '../../Component/main/sidebar/SideBar';

const Task = () => {
  const params=useParams();
  return (
    <div className='task'>
      <SideBar />
      <Form id={params.id} listName={params.listName} />
      <TaskList tableName={params.listName} id={params.id} />
    </div>
  );
}

export default Task;
