import {Drawer, Input, Col, Select, Form, Row, Button, Spin} from 'antd';
import {LoadingOutlined} from "@ant-design/icons";
import {useState} from "react";
import {addNewExam, addNewGrade} from "../client";
import {errorNotification, successNotification} from "../common/Notification";


const {Option} = Select;
const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;
function GradeDrawerForm({showDrawer, setShowDrawer, fetchGrades}) {
    const onCLose = () => setShowDrawer(false);
    const [submitting, setSubmitting] = useState(false)

    const onFinish = grade => {
        setSubmitting(true)
        console.log(JSON.stringify(grade, null, 2));
        addNewGrade(grade)
            .then(() => {
                console.log("grade added")
                onCLose();
                successNotification("grade successfully added")
                fetchGrades();
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
        title="Create new grade"
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
                        name="studentId"
                        label="student id"
                        rules={[{required: true, message: 'Please enter student id'}]}
                    >
                        <Input placeholder="Please enter student id"/>
                    </Form.Item>
                </Col>

                <Col span={12}>
                    <Form.Item
                        name="examId"
                        label="exam id"
                        rules={[{required: true, message: 'Please enter exam id'}]}
                    >
                        <Input placeholder="Please enter exam id"/>
                    </Form.Item>
                </Col>
                <Col span={12}>
                    <Form.Item
                        name="value"
                        label="value"
                        rules={[{required: true, message: 'Please enter a value'}]}
                    >
                        <Select placeholder="Please enter a value">
                            <Option value="2.0">2.0</Option>
                            <Option value="2.5">2.5</Option>
                            <Option value="3.0">3.0</Option>
                            <Option value="3.5">3.5</Option>
                            <Option value="4.0">4.0</Option>
                            <Option value="4.5">4.5</Option>
                            <Option value="5.0">5.0</Option>
                        </Select>
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

export default GradeDrawerForm;