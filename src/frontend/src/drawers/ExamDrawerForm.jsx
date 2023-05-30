import {Drawer, Input, Col, Select, Form, Row, Button, Spin} from 'antd';
import {LoadingOutlined} from "@ant-design/icons";
import {useState} from "react";
import {addNewExam} from "../../../../../usos/src/frontend/src/pages/client";
import {errorNotification, successNotification} from "../common/Notification";


const {Option} = Select;
const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;
function ExamDrawerForm({showDrawer, setShowDrawer, fetchExams}) {
    const onCLose = () => setShowDrawer(false);
    const [submitting, setSubmitting] = useState(false)

    const onFinish = exam => {
        setSubmitting(true)
        console.log(JSON.stringify(exam, null, 2));
        addNewExam(exam)
            .then(() => {
                console.log("exam added")
                onCLose();
                successNotification("exam successfully added")
                fetchExams();
            })
            .catch(err => {
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
        title="Create new exam"
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
                        name="courseId"
                        label="course id"
                        rules={[{required: true, message: 'Please enter course id'}]}
                    >
                        <Input placeholder="Please enter course id"/>
                    </Form.Item>
                </Col>

                <Col span={12}>
                    <Form.Item
                        name="startsAt"
                        label="starts at"
                        rules={[{required: true, message: 'Please enter starts at'}]}
                    >
                        <Input placeholder="Please enter starts at"/>
                    </Form.Item>
                </Col>
                <Col span={12}>
                    <Form.Item
                        name="name"
                        label="name"
                        rules={[{required: true, message: 'Please enter name'}]}
                    >
                        <Input placeholder="Please enter name"/>
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

export default ExamDrawerForm;