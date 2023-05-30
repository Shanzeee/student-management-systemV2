import {useEffect, useState} from "react";
import {getAllExams, getAllGroups} from "../../../../../usos/src/frontend/src/pages/client";
import {Badge, Button, Empty, Table, Tag} from "antd";
import {PlusOutlined} from "@ant-design/icons";
import GroupDrawerForm from "../../../../../usos/src/frontend/src/drawers/GroupDrawerForm";
import ExamDrawerForm from "../drawers/ExamDrawerForm";

const columns = [

    {
        title: 'Id',
        dataIndex: 'id',
        key: 'id',
    },
    {
        title: 'starts at',
        dataIndex: 'startsAt',
        key: 'startsAt',
    },
    {
        title: 'exam name',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: 'course name',
        dataIndex: 'courseName',
        key: 'courseName',
    },
    {
        title: 'course id',
        dataIndex: 'courseId',
        key: 'courseId',
    }

];


const ExamPage = () => {
    const [exams, setExams] = useState([]);
    const [collapsed, setCollapsed] = useState(false);
    const [showDrawer, setShowDrawer] = useState(false);
    const fetchExams = () =>
        getAllExams()
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setExams(data);
            })

    useEffect(() => {
        console.log("component is mounted");
        fetchExams();
    }, [])

    const renderExam = () => {

        return <>
            <ExamDrawerForm
                showDrawer={showDrawer}
                setShowDrawer={setShowDrawer}
                fetchGroups={fetchExams}
            />
            <Table
                dataSource={exams}
                columns={columns}
                bordered
                title={() =>
                    <>
                        <Tag style={{marginLeft: "10px"}}>Number of Exams</Tag>
                        <Badge count={exams.length} className="site-badge-count-4"/>
                        <br/><br/>
                        <Button
                            onClick={() => setShowDrawer(!showDrawer)}
                            type="primary" shape="round" icon={<PlusOutlined/>} size="small">
                            Add New Exam
                        </Button>
                    </>

                }
                pagination={{pageSize: 50}}
                scroll={{y: 500}}
                rowKey={course => course.id}
            />
        </>

    }
    return renderExam()
};

export {ExamPage};