import './App.css';
import SideBar from './components/SideBar';

console.log(sessionStorage.getItem("userId"));

function App() {
  return (
      <SideBar/>
  );
}

export default App;
