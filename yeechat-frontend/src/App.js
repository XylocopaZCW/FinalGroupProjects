import './App.css';
import {SideBar} from './components/SideBar/SideBar';
import {Grid} from "semantic-ui-react";
import Messages from "./components/Messages/Messages"


console.log(sessionStorage.getItem('userId'));
console.log(sessionStorage.getItem('username'));

function App() {
    return (
        <Grid columns="equal">
            <SideBar />
            <Grid.Column width={3}>
            <span></span>
            </Grid.Column>
            <Grid.Column className="messagepanel">
                <Messages/>
            </Grid.Column>
            <Grid.Column width={1}>
                <span></span>
            </Grid.Column>
        </Grid>

    );
 }

export default App;
