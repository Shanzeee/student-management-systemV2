import {
    AntDesignOutlined,
    AppstoreOutlined,
    BookOutlined,
    KeyOutlined,
    MailOutlined,
    SettingOutlined,
    UserOutlined
} from '@ant-design/icons';
import logo from '../logo.svg';
import { Menu } from 'antd';
import { useState } from 'react';
import {NavLink} from "react-router-dom";
const items = [
    {
        label: (
            <a href="https://ant.design" target="_blank" rel="noopener noreferrer">
                React
            </a>
        ),
        icon: <AntDesignOutlined />,
        key: 'link',
    },
    {
        label: 'Admin - Submenu',
        key: 'SubMenu',
        icon: <KeyOutlined />,
        children: [
            {
                type: 'group',
                label: '',
                children: [
                    {
                        label: (<NavLink to="/departments" className="nav-link">
                            Department
                        </NavLink>),
                        key: 'setting:1',

                    },
                    {
                        label: (<NavLink to="/groups" className="nav-link">
                            Groups
                        </NavLink>),
                        key: 'setting:2',
                    },
                    {
                        label: (<NavLink to="/courses" className="nav-link">
                            Course
                        </NavLink>),
                        key: 'setting:3',
                    },
                ],
            },

        ],
    },
    {
        label: 'Teacher',
        key: 'Teacher',
        icon: <BookOutlined />,
        children: [
            {
                type: 'group',
                label: '',
                children: [
                    {
                        label: (<NavLink to="/teachers" className="nav-link">
                            Teachers
                        </NavLink>),
                        key: 'setting:20',
                    },
                    {
                        label: (<NavLink to="/exams" className="nav-link">
                            Exams
                        </NavLink>),
                        key: 'setting:21',
                    },
                    {
                        label: (<NavLink to="/grades" className="nav-link">
                            Grades
                        </NavLink>),
                        key: 'setting:22',
                    },
                ],
            },

        ],
    },
    {
        label: 'Student',
        key: 'Student',
        icon: <UserOutlined />,
        children: [
            {
                type: 'group',
                label: '',
                children: [
                    {
                        label: (<NavLink to="/students" className="nav-link">
                            Student
                        </NavLink>),
                        key: 'setting:31',
                    },
                    {
                        label: (<NavLink to="/groups" className="nav-link">
                            Group
                        </NavLink>),
                        key: 'setting:32',
                    },
                    {
                        label: (<NavLink to="/grades" className="nav-link">
                            Grade
                        </NavLink>),
                        key: 'setting:33',
                    },
                ],
            },

        ],
    },

];
const Header = () => {
    const [current, setCurrent] = useState('mail');
    const onClick = (e) => {
        console.log('click ', e);
        setCurrent(e.key);
    };
    return <Menu onClick={onClick} selectedKeys={[current]} mode="horizontal" items={items} />;
};
export {Header};


// const NavBar = () => {
//     return (
//         <nav className="navbar navbar-expand navbar-dark bg-dark">
//             <a href="https://reactjs.org" className="navbar-brand ms-1">
//                 <img src={logo} className="App-logo" alt="logo" />
//                 React
//             </a>
//
//             <div className="navbar-nav me-auto">
//
//
//                 <li className="nav-item">
//                     <NavLink to="/home" className="nav-link">
//                         Home
//                     </NavLink>
//                 </li>
//             </div>
//
//
//             {
//                 <div className="navbar-nav ms-auto">
//                     <li className="nav-item">
//                         <NavLink to="/register" className="nav-link">
//                             Sign Up
//                         </NavLink>
//                     </li>
//
//                     <li className="nav-item">
//                         <NavLink to="/login" className="nav-link">
//                             Sign In
//                         </NavLink>
//                     </li>
//                 </div>
//             }
//
//             {
//                 <div className="navbar-nav ms-auto">
//                     <li className="nav-item">
//
//                     </li>
//
//
//                 </div>
//             }
//
//         </nav>
//     );
// };

