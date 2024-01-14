import './App.css';
import {SideBar} from './components/SideBar/SideBar';
import {Message} from "semantic-ui-react";

console.log(sessionStorage.getItem('userId'));
console.log(sessionStorage.getItem('username'));

function App() {
  return (
      <div>
        <SideBar/>
          <Message/>
      </div>
  );
}

export default App;
