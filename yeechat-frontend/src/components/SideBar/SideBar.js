import React from 'react';
// import Box from '@mui/material/Box';
// import Drawer from '@mui/material/Drawer';
// import CssBaseline from '@mui/material/CssBaseline';
// import AppBar from '@mui/material/AppBar';
// import Toolbar from '@mui/material/Toolbar';
// import List from '@mui/material/List';
// import Typography from '@mui/material/Typography';
// import Divider from '@mui/material/Divider';
// import ListItem from '@mui/material/ListItem';
// import ListItemButton from '@mui/material/ListItemButton';
// import ListItemIcon from '@mui/material/ListItemIcon';
// import ListItemText from '@mui/material/ListItemText';
// import InboxIcon from '@mui/icons-material/MoveToInbox';
// import MailIcon from '@mui/icons-material/Mail';

import {Menu} from 'semantic-ui-react';
import "./SideBar.css";
import UserInfo from './UserInfo/UserInfo';
import Workspaces from './Workspaces/Workspaces';
import Channels from "./Channels/Channels";

export const SideBar = () => {
    return (
        <Menu vertical fixed="left" borderless size="massive" className="side_bar">
            <UserInfo/>
            <hr/>
            <Workspaces/>
            <hr/>
            <Channels/>
        </Menu>
    )
}

// const drawerWidth = 240;
//
// export default function PermanentDrawerLeft() {
//     return (
//         <Box sx={{ display: 'flex' }}>
//             <CssBaseline />
//             <AppBar
//                 position="fixed"
//                 sx={{ width: `calc(100% - ${drawerWidth}px)`, ml: `${drawerWidth}px` }}
//             >
//                 <Toolbar>
//                     <Typography variant="h4" noWrap component="div" id="">
//                         YeeChat
//                     </Typography>
//                 </Toolbar>
//             </AppBar>
//             <Drawer
//                 sx={{
//                     width: drawerWidth,
//                     flexShrink: 0,
//                     '& .MuiDrawer-paper': {
//                         width: drawerWidth,
//                         boxSizing: 'border-box',
//                     },
//                 }}
//                 variant="permanent"
//                 anchor="left"
//             >
//                 <Toolbar />
//                 <Divider />
//                 <List>
//                     {['Channels'].map((text, index) => (
//                         <ListItem key={text} disablePadding>
//                             <ListItemButton>
//                                 <ListItemIcon>
//                                     {index % 2 === 0 ? <InboxIcon /> : <MailIcon />}
//                                 </ListItemIcon>
//                                 <ListItemText primary={text} />
//                             </ListItemButton>
//                         </ListItem>
//                     ))}
//                 </List>
//                 <Divider />
//                 <List>
//                     {['Direct Messages'].map((text, index) => (
//                         <ListItem key={text} disablePadding>
//                             <ListItemButton>
//                                 <ListItemIcon>
//                                     {index % 2 === 0 ? <InboxIcon /> : <MailIcon />}
//                                 </ListItemIcon>
//                                 <ListItemText primary={text} />
//                             </ListItemButton>
//                         </ListItem>
//                     ))}
//                 </List>
//             </Drawer>
//             <Box
//                 component="main"
//                 sx={{ flexGrow: 1, bgcolor: 'background.default', p: 3 }}
//             >
//                 <Toolbar />
//
//             </Box>
//         </Box>
//     );
// }