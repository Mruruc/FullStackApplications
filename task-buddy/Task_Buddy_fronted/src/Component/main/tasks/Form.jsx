import axios from 'axios';
import { useState } from 'react';
import postRequest from '../postRequest';

const Form = (props) => {

  const [task, setTask] = useState({ taskName: '', date: '' });
  const [isSent, setSent] = useState(0);
  const [isError, setError] = useState(null);

  const nameInput = (e) => {
    setTask({ ...task, taskName: e.target.value });
  };
  const dateInput = (e) => {
    setTask({ ...task, date: e.target.value });
  };


  const saveData = (e) => {
    e.preventDefault();

    postRequest(
      'http://localhost:8080/api/task',
      {
        taskName: task.taskName,
        completed: false,
        dateTime: task.date,
        tasks: {
          id: props.id,
          listName: props.listName,
        },
      },
      setSent,
      setError
    );

    setTask({ taskName: '', date: '' });
  };

  return (
    <div className='form-part'>
      <form className='form' onSubmit={saveData}>
        <div className='form-header'>Add Task</div>
        <div className='form-control'>
          <input
            type='text'
            placeholder='Task Name'
            value={task.taskName}
            required
            className='form-input'
            onChange={nameInput}
          />
        </div>

        <div className='form-control'>
          <input
            type='datetime-local'
            value={task.date}
            className='form-input'
            onChange={dateInput}
          />
        </div>
        <button type='submit' className='btn'>
          Save
        </button>
      </form>

    </div>
  );
};

export default Form;
