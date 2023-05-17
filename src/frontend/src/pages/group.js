import {useEffect, useState} from "react";
import {getAllGroups} from "../client";
import {Badge, Button, Empty, Table, Tag} from "antd";
import {PlusOutlined} from "@ant-design/icons";
import GroupDrawerForm from "../drawers/GroupDrawerForm";

const columns = [

    {
        title: 'Id',
        dataIndex: 'id',
        key: 'id',
    },
    {
        title: 'group name',
        dataIndex: 'groupName',
        key: 'groupName',
    },
    {
        title: 'department',
        dataIndex: 'departmentShortcut',
        key: 'departmentShortcut',
    }

];


const GroupPage = () => {
    const [groups, setGroups] = useState([]);
    const [collapsed, setCollapsed] = useState(false);
    const [showDrawer, setShowDrawer] = useState(false);
    const fetchGroups = () =>
        getAllGroups()
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setGroups(data);
            })

    useEffect(() => {
        console.log("component is mounted");
        fetchGroups();
    }, [])

    const renderGroup = () => {

        return <>
            <GroupDrawerForm
                showDrawer={showDrawer}
                setShowDrawer={setShowDrawer}
                fetchGroups={fetchGroups}
            />
            <Table
                dataSource={groups}
                columns={columns}
                bordered
                title={() =>
                    <>
                        <Tag style={{marginLeft: "10px"}}>Number of groups</Tag>
                        <Badge count={groups.length} className="site-badge-count-4"/>
                        <br/><br/>
                        <Button
                            onClick={() => setShowDrawer(!showDrawer)}
                            type="primary" shape="round" icon={<PlusOutlined/>} size="small">
                            Add New Group
                        </Button>
                    </>

                }
                pagination={{pageSize: 50}}
                scroll={{y: 500}}
                rowKey={course => course.id}
            />
        </>

    }
    return renderGroup()
};

export {GroupPage};