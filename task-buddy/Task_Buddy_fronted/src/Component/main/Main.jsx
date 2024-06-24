import React from 'react';
import Form from './tasks/Form';
import TaskList from './tasks/TaskList';
import AddTaskList from './sidebar/AddTaskList';
import SideBar from './sidebar/SideBar';

const Main = () => {
  return (
    <div className='main'>
      <SideBar />
      <AddTaskList />
    </div>
  );
};

export default Main;
