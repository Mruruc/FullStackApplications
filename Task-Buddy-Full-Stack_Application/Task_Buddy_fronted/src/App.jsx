import Header from './Component/header/Header';
import Main from './Component/main/Main';
import Footer from './Component/footer/Footer';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './assets/pages/Home';
import Task from './assets/pages/Task';

function App() {
  return (
    <>
      <BrowserRouter>
        <Header />

        <Routes>
          <Route>
            <Route index element={<Home />} />
            <Route path='/task/:listName/:id' element={<Task />} />
          </Route>
        </Routes>

        <Footer />
      </BrowserRouter>
    </>
  );
}

export default App;
