import {getAllDepartments, getAllStudents, getAllTeachers} from "../client";
import {useState, useEffect} from "react";
import {Badge, Button, Empty, Table, Tag} from "antd";
import {PlusOutlined} from "@ant-design/icons";
import StudentDrawerForm from "../drawers/StudentDrawerForm";

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
    {
        title: 'group name',
        dataIndex: 'groupName',
        key: 'groupName',
    }

];



const StudentPage = () => {
    const [teachers, setTeachers] = useState([]);
    const [collapsed, setCollapsed] = useState(false);
    const [showDrawer, setShowDrawer] = useState(false);
    const fetchStudents = () =>
        getAllStudents()
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setTeachers(data);
            })

    useEffect(() => {
        console.log("component is mounted");
        fetchStudents();
    }, [])

    const renderStudents = () => {

        return <>
            <StudentDrawerForm
                showDrawer={showDrawer}
                setShowDrawer={setShowDrawer}
                fetchStudents={fetchStudents}
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

    return renderStudents()
};

export {StudentPage};