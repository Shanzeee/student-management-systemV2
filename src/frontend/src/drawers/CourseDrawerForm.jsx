import {Drawer, Input, Col, Select, Form, Row, Button, Spin} from 'antd';
import {LoadingOutlined} from "@ant-design/icons";
import {useState} from "react";
import {createNewCourse} from "../client";
import {errorNotification, successNotification} from "../common/Notification";


const {Option} = Select;
const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;
function CourseDrawerForm({showDrawer, setShowDrawer, fetchCourses}) {
    const onCLose = () => setShowDrawer(false);
    const [submitting, setSubmitting] = useState(false)

    const onFinish = course => {
        setSubmitting(true)
        console.log(JSON.stringify(course, null, 2));
        createNewCourse(course)
            .then(() => {
                console.log("course added")
                onCLose();
                successNotification("Course successfully added", `${course.name} was added to system`)
                fetchCourses();
            }).catch(err => {
            console.log(err);
            err.response.json().then(res => {
                console.log(res);
                errorNotification(
                    "There was an issue",
                    `${res.message} [${res.status}] [${res.error}]`,
                    "bottomLeft"
                )
            });
        }).finally(() => {
            setSubmitting(false)
        })
    };

    const onFinishFailed = errorInfo => {
        alert(JSON.stringify(errorInfo, null, 2));
    };

    return <Drawer
        title="Create new course"
        width={720}
        onClose={onCLose}
        visible={showDrawer}
        bodyStyle={{paddingBottom: 80}}
        footer={
            <div
                style={{
                    textAlign: 'right',
                }}
            >
                <Button onClick={onCLose} style={{marginRight: 8}}>
                    Cancel
                </Button>
            </div>
        }
    >
        <Form layout="vertical"
              onFinishFailed={onFinishFailed}
              onFinish={onFinish}
              hideRequiredMark>
            <Row gutter={16}>
                <Col span={12}>
                    <Form.Item
                        name="name"
                        label="name"
                        rules={[{required: true, message: 'Please enter course name'}]}
                    >
                        <Input placeholder="Please enter course name"/>
                    </Form.Item>
                </Col>

                <Col span={12}>
                    <Form.Item
                        name="groupId"
                        label="group id"
                        rules={[{required: true, message: 'Please enter group id'}]}
                    >
                        <Input placeholder="Please enter group id"/>
                    </Form.Item>
                </Col>
                <Col span={12}>
                    <Form.Item
                        name="teacherId"
                        label="teacher id"
                        rules={[{required: true, message: 'Please enter teacher id'}]}
                    >
                        <Input placeholder="Please enter teacher id"/>
                    </Form.Item>
                </Col>
            </Row>
            <Row>
                <Col span={12}>
                    <Form.Item >
                        <Button type="primary" htmlType="submit">
                            Submit
                        </Button>
                    </Form.Item>
                </Col>
            </Row>
            <Row>
                {submitting && <Spin indicator={antIcon} />}
            </Row>
        </Form>
    </Drawer>
}

export default CourseDrawerForm;