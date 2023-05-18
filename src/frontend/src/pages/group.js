import {useEffect, useState} from "react";
import {deleteGroup, getAllGroups} from "../client";
import {Badge, Button, Empty, Table, Tag, Radio,Popconfirm} from "antd";
import {PlusOutlined} from "@ant-design/icons";
import GroupDrawerForm from "../drawers/GroupDrawerForm";
import {errorNotification, successNotification} from "../common/Notification";

const columns = fetchGroups => [

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
    },
    {
        title: 'Actions',
        key: 'actions',
        render: (text, group) =>
            <Radio.Group>
                <Popconfirm
                    placement='topRight'
                    title={`Are you sure to delete ${group.groupName}`}
                    onConfirm={() => removeGroup(group.id, fetchGroups)}
                    okText='Yes'
                    cancelText='No'>
                    <Radio.Button value="small">Delete</Radio.Button>
                </Popconfirm>
                <Radio.Button onClick={() => alert("TODO: Implement edit group")} value="small">Edit</Radio.Button>
            </Radio.Group>
    }

];

const removeGroup = (groupId, callback) => {
    deleteGroup(groupId).then(() => {
        successNotification("Group deleted", `Group with ${groupId} was deleted`);
        callback();
    }).catch(err => {
        err.response.json().then(res => {
            console.log(res);
            errorNotification(
                "There was an issue",
                `${res.message} [${res.status}] [${res.error}]`
            )
        });
    })
}


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
                columns={columns(fetchGroups)}
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