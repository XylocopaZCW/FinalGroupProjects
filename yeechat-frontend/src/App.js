import './App.css';
import {SideBar} from './components/SideBar/SideBar';

console.log(sessionStorage.getItem('userId'));
console.log(sessionStorage.getItem('username'));

function App() {
  return (
      <div>
        <SideBar/>
      </div>
  );
}

export default App;
