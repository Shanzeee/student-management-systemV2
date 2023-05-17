import {Drawer, Input, Col, Select, Form, Row, Button, Spin} from 'antd';
import {LoadingOutlined} from "@ant-design/icons";
import {useState} from "react";
import {createNewDepartment} from "../client";
import {successNotification, errorNotification} from "../common/Notification";


const {Option} = Select;
const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;
function DepartmentDrawerForm({showDrawer, setShowDrawer, fetchDepartments}) {
    const onCLose = () => setShowDrawer(false);
    const [submitting, setSubmitting] = useState(false)

    const onFinish = department => {
        setSubmitting(true)
        console.log(JSON.stringify(department, null, 2));
        createNewDepartment(department)
            .then(() => {
                console.log("department added")
                onCLose();
                successNotification(
                    "Student successfully added",
                    `${department.name} was added to the system`
                )
                fetchDepartments();
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
            setSubmitting(false);
        })
    };

    const onFinishFailed = errorInfo => {
        alert(JSON.stringify(errorInfo, null, 2));
    };

    return <Drawer
        title="Create new department"
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
                        rules={[{required: true, message: 'Please enter department full name'}]}
                    >
                        <Input placeholder="Please enter department full name"/>
                    </Form.Item>
                </Col>
                <Col span={12}>
                    <Form.Item
                        name="shortcut"
                        label="Shortcut"
                        rules={[{required: true, message: 'Please enter department shortcut'}]}
                    >
                        <Input placeholder="Please enter department shortcut"/>
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

export default DepartmentDrawerForm;