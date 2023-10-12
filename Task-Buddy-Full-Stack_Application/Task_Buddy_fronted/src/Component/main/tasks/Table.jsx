import React from 'react';
import { useState, useEffect } from 'react';
import Task from './Task';


const Table = ({tableName,tasks,removeItem,handleCheckBox,isError,isLoading}) => {
 

  return (
    <>
      <table className='section-center'>
        <caption className='table-name'>{tableName}</caption>

        <thead className='table-header'>
          {tasks.length > 0 && (
            <tr>
              <th>COMPLETED</th>
              <th>NAME</th>
              <th>DATE</th>
              <th>REMOVE</th>
            </tr>
          )}
        </thead>

        <tbody>
          {tasks.map((task) => {
            return (
              <Task
                key={task.id}
                taskName={task.taskName}
                date={task.dateTime}
                isCompleted={task.completed}
                removeItem={() => removeItem(task.id)}
                handleCheckBox={() => handleCheckBox(task.id)}
              />
            );
          })}
        </tbody>
      </table>
      {isError && <h1>{isError}</h1>}
      {isLoading && <h1>Loading...</h1>}
    </>
  );
};

export default Table;
