import {getAllDepartments} from "../client";
import {useState, useEffect} from "react";
import {Badge, Button, Empty, Table, Tag} from "antd";
import {PlusOutlined} from "@ant-design/icons";
import DepartmentDrawerForm from "../drawers/DepartmentDrawerForm";
import {errorNotification} from "../common/Notification";

const columns = [

    {
        title: 'Id',
        dataIndex: 'id',
        key: 'id',
    },
    {
        title: 'name',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: 'Shortcut',
        dataIndex: 'shortcut',
        key: 'shortcut',
    },

];



const DepartmentPage = () => {
    const [departments, setDepartments] = useState([]);
    const [collapsed, setCollapsed] = useState(false);
    const [fetching, setFetching] = useState(true);
    const [showDrawer, setShowDrawer] = useState(false);
    const fetchDepartments = () =>
        getAllDepartments()
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setDepartments(data);
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
        fetchDepartments();
    }, []);

    const renderDepartments = () => {

        return <>
            <DepartmentDrawerForm
                showDrawer={showDrawer}
                setShowDrawer={setShowDrawer}
                fetchDepartments={fetchDepartments}
            />
            <Table
                dataSource={departments}
                columns={columns}
                bordered
                title={() =>
                    <>
                        <Tag style={{marginLeft: "10px"}}>Number of departments</Tag>
                        <Badge count={departments.length} className="site-badge-count-4"/>
                        <br/><br/>
                        <Button
                            onClick={() => setShowDrawer(!showDrawer)}
                            type="primary" shape="round" icon={<PlusOutlined/>} size="small">
                            Add New Department
                        </Button>
                    </>

                }
                pagination={{pageSize: 50}}
                scroll={{y: 500}}
                rowKey={department => department.id}
            />
        </>

    }

    return renderDepartments()
};

export {DepartmentPage};