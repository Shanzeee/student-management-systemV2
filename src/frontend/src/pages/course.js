import {useEffect, useState} from "react";
import {getAllCourses} from "../client";
import {Badge, Button, Empty, Table, Tag} from "antd";
import {PlusOutlined} from "@ant-design/icons";
import CourseDrawerForm from "../drawers/CourseDrawerForm";

const columns = [

    {
        title: 'Id',
        dataIndex: 'id',
        key: 'id',
    },
    {
        title: 'course name',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: 'group id',
        dataIndex: 'groupId',
        key: 'groupId',
    },
    {
        title: 'teacher name',
        dataIndex: 'teacherName',
        key: 'teacherName',
    },

];


const CoursePage = () => {
    const [course, setCourses] = useState([]);
    const [collapsed, setCollapsed] = useState(false);
    const [showDrawer, setShowDrawer] = useState(false);
    const fetchCourses = () =>
        getAllCourses()
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setCourses(data);
            })

    useEffect(() => {
        console.log("component is mounted");
        fetchCourses();
    }, [])

    const renderCourse = () => {

        return <>
            <CourseDrawerForm
                showDrawer={showDrawer}
                setShowDrawer={setShowDrawer}
                fetchCourses={fetchCourses}
            />
            <Table
                dataSource={course}
                columns={columns}
                bordered
                title={() =>
                    <>
                        <Tag style={{marginLeft: "10px"}}>Number of courses</Tag>
                        <Badge count={course.length} className="site-badge-count-4"/>
                        <br/><br/>
                        <Button
                            onClick={() => setShowDrawer(!showDrawer)}
                            type="primary" shape="round" icon={<PlusOutlined/>} size="small">
                            Add New Course
                        </Button>
                    </>

                }
                pagination={{pageSize: 50}}
                scroll={{y: 500}}
                rowKey={course => course.id}
            />
        </>

    }
    return renderCourse()
};

export {CoursePage};