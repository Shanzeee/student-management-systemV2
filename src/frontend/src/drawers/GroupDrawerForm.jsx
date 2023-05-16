import {Drawer, Input, Col, Select, Form, Row, Button, Spin} from 'antd';
import {LoadingOutlined} from "@ant-design/icons";
import {useState} from "react";
import {createNewGroup} from "../client";


const {Option} = Select;
const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;
function GroupDrawerForm({showDrawer, setShowDrawer, fetchGroups}) {
    const onCLose = () => setShowDrawer(false);
    const [submitting, setSubmitting] = useState(false)

    const onFinish = group => {
        setSubmitting(true)
        console.log(JSON.stringify(group, null, 2));
        createNewGroup(group)
            .then(() => {
                console.log("group added")
                onCLose();
                fetchGroups();
            }).catch(err => {
            console.log(err)
        }).finally(() => {
            setSubmitting(false)
        })
    };

    const onFinishFailed = errorInfo => {
        alert(JSON.stringify(errorInfo, null, 2));
    };

    return <Drawer
        title="Create new group"
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
                        name="groupName"
                        label="group name"
                        rules={[{required: true, message: 'Please enter group name'}]}
                    >
                        <Input placeholder="Please enter group name"/>
                    </Form.Item>
                </Col>
                <Col span={12}>
                    <Form.Item
                        name="departmentShortcut"
                        label="department shortcut"
                        rules={[{required: true, message: 'Please enter department shortcut '}]}
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

export default GroupDrawerForm;