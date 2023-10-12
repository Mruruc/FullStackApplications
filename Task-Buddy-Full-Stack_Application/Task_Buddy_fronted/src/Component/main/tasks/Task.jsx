import './tableStyle.css';

const Task = ({ taskName, date, isCompleted, removeItem, handleCheckBox }) => {

  return (
    <tr
      style={{
        textTransform: 'capitalize',
        textDecoration: isCompleted && 'line-through',
      }}
    >
      <td>
        <input
          type='checkbox'
          className='pointer'
          checked={isCompleted}
          onChange={handleCheckBox}
        />
      </td>

      <td>{taskName}</td>

      <td>{date}</td>

      <td>
        <button className='btn-remove' type='button' onClick={removeItem}>
          Remove
        </button>
      </td>
    </tr>
  );
};

export default Task;
