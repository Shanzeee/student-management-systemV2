import {getAllDepartments, getAllTeachers} from "../client";
import {useState, useEffect} from "react";
import {Badge, Button, Empty, Table, Tag} from "antd";
import {PlusOutlined} from "@ant-design/icons";
import TeacherDrawerForm from "../drawers/TeacherDrawerForm";

const columns = [

    {
        title: 'Id',
        dataIndex: 'id',
        key: 'id',
    },
    {
        title: 'first name',
        dataIndex: 'firstName',
        key: 'firstName',
    },
    {
        title: 'last name',
        dataIndex: 'lastName',
        key: 'lastName',
    },
    {
        title: 'email',
        dataIndex: 'email',
        key: 'email',
    },
    {
        title: 'gender',
        dataIndex: 'gender',
        key: 'gender',
    },

];



const TeacherPage = () => {
    const [teachers, setTeachers] = useState([]);
    const [collapsed, setCollapsed] = useState(false);
    const [showDrawer, setShowDrawer] = useState(false);
    const fetchTeachers = () =>
        getAllTeachers()
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setTeachers(data);
            })

    useEffect(() => {
        console.log("component is mounted");
        fetchTeachers();
    }, [])

    const renderTeachers = () => {

        return <>
            <TeacherDrawerForm
                showDrawer={showDrawer}
                setShowDrawer={setShowDrawer}
                fetchTeachers={fetchTeachers}
            />
            <Table
                dataSource={teachers}
                columns={columns}
                bordered
                title={() =>
                    <>
                        <Tag style={{marginLeft: "10px"}}>Number of teachers</Tag>
                        <Badge count={teachers.length} className="site-badge-count-4"/>
                        <br/><br/>
                        <Button
                            onClick={() => setShowDrawer(!showDrawer)}
                            type="primary" shape="round" icon={<PlusOutlined/>} size="small">
                            Add New Teacher
                        </Button>
                    </>

                }
                pagination={{pageSize: 50}}
                scroll={{y: 500}}
                rowKey={teacher => teacher.id}
            />
        </>

    }

    return renderTeachers()
};

export {TeacherPage};