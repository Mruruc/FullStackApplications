import { useState } from 'react';
import './sidebar.css';
import useGetRequest from '../useGetRequest';
import { Link } from 'react-router-dom';

const url = 'http://localhost:8080/api/tasksList';

const SideBar = () => {
  const { data: list, isLoading, isError } = useGetRequest( url );

  return (
    <>
      <ul className='sidebar'>
        <div className='list-header'>
          <h2>Tasks </h2>
          {isLoading && <h2>Loading...</h2>}
          {isError && <h2>{isError}</h2>}
        </div>

        {list.map((e) => {
          return (
            <div key={e.id}>
              <Link to={`/task/${e.listName}/${e.id}`}>
                <li>{e.listName}</li>;
              </Link>
            </div>
          );
        })}
      </ul>
    </>
  );
};

export default SideBar;
