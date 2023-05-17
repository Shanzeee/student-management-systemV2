import {getAllDepartments, getAllGrades} from "../client";
import {useState, useEffect} from "react";
import {Badge, Button, Empty, Table, Tag} from "antd";
import {PlusOutlined} from "@ant-design/icons";
import DepartmentDrawerForm from "../drawers/DepartmentDrawerForm";
import {errorNotification} from "../common/Notification";
import GradeDrawerForm from "../drawers/GradeDrawerForm";

const columns = [

    {
        title: 'Id',
        dataIndex: 'id',
        key: 'id',
    },
    {
        title: 'course name',
        dataIndex: 'courseName',
        key: 'courseName',
    },
    {
        title: 'exam name',
        dataIndex: 'examName',
        key: 'examName',
    },
    {
        title: 'value',
        dataIndex: 'value',
        key: 'value',
    },
    {
        title: 'teacher name',
        dataIndex: 'teacherName',
        key: 'teacherName',
    },
    {
        title: 'student id',
        dataIndex: 'studentId',
        key: 'studentId',
    }

];



const GradePage = () => {
    const [grades, setGrades] = useState([]);
    const [collapsed, setCollapsed] = useState(false);
    const [fetching, setFetching] = useState(true);
    const [showDrawer, setShowDrawer] = useState(false);
    const fetchGrades = () =>
        getAllGrades()
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setGrades(data);
            }).catch(err => {
            console.log(err.response)
            err.response.json().then(res => {
                console.log(res);
                errorNotification(
                    "There was an issue",
                    `${res.message} [${res.status}] [${res.error}]`
                )
            });
        }).finally(()=> setFetching(false))

    useEffect(() => {
        console.log("component is mounted");
        fetchGrades();
    }, []);

    const renderGrades = () => {

        return <>
            <GradeDrawerForm
                showDrawer={showDrawer}
                setShowDrawer={setShowDrawer}
                fetchDepartments={fetchGrades}
            />
            <Table
                dataSource={grades}
                columns={columns}
                bordered
                title={() =>
                    <>
                        <Tag style={{marginLeft: "10px"}}>Number of grades</Tag>
                        <Badge count={grades.length} className="site-badge-count-4"/>
                        <br/><br/>
                        <Button
                            onClick={() => setShowDrawer(!showDrawer)}
                            type="primary" shape="round" icon={<PlusOutlined/>} size="small">
                            Add New Grade
                        </Button>
                    </>

                }
                pagination={{pageSize: 50}}
                scroll={{y: 500}}
                rowKey={grade => grade.id}
            />
        </>

    }

    return renderGrades()
};

export {GradePage};