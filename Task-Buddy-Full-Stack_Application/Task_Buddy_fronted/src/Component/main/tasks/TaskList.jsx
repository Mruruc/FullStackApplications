import './tableStyle.css';
import React, { useEffect, useState } from 'react';
import Task from './Task';
import useGetRequest from '../useGetRequest';
import axios from 'axios';
import Table from './Table';

const TaskList = (props) => {

   const [id, setId] = useState(props.id);

   useEffect(() => {
     setId(props.id);
   }, [props.id]);


   const url = `http://localhost:8080/api/${id}`;

   const { data, isLoading, isError } = useGetRequest(url);

   const [tasks, setTasks] = useState(data);

   useEffect(() => {
     setTasks(data);
   }, [data,url]);



  const removeItem = (id) => {
    const newTasks = tasks.filter((task) => task.id !== id);
    setTasks(newTasks);
    remove(id);
  };

 const remove = async (id) => {
   axios.delete(`http://localhost:8080/api/task/${id}`);
 };
 
  const handleCheckBox = (id) => {
    const newTasks = tasks.map((task) => {
      if (task.id === id) {
       const obj= { ...task, isCompleted: !task.isCompleted };
       update(obj);
        return obj;
      }
      return task;
    });
  
    setTasks(newTasks);
  };

  const update= async (task)=>{
    const res = await axios.patch('http://localhost:8080/api/task',task);
    console.log(res);
  } 


  return (
    <div>
      <Table tableName={props.tableName} 
             tasks={tasks} 
             removeItem={removeItem} 
             handleCheckBox={handleCheckBox}
             isError={isError} 
             isLoading={isLoading}
             />
    </div>
  );
};

export default TaskList;
