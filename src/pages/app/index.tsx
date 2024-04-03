import {list, remove,create} from './service';
import {DeleteFilled, EditOutlined, PlusOutlined, ReloadOutlined, SettingOutlined} from '@ant-design/icons';
import type {ActionType, ProColumns} from '@ant-design/pro-components';
import {PageContainer, ProTable} from '@ant-design/pro-components';
import {Button, message, Modal} from 'antd';
import React, {useRef, useState} from 'react';
import Index from "./components/Setting";

export default () => {
  const actionRef = useRef<ActionType>();
  const [selectedRowKeys, setSelectedRowKeys] = useState<React.Key[]>([]);
  const [settingModalVisible, setSettingModalVisible] = useState<boolean>(true);
  const [values, setValues] = useState<Record<string, any>>({id:10});
  const handleRemove = (id?: number) => {
    Modal.confirm({
      title: '提醒',
      content: '您正在删除数据',
      onOk: () => {
        remove({
          ids: id || selectedRowKeys.join(','),
        }).then((result) => {
          if (result.code === 0) {
            message.success(result.msg).then();
            actionRef.current?.reload();
          } else {
            message.error(result.msg).then();
          }
        });
      },
    });
  };
  const columns: ProColumns<Record<string, any>>[] = [
    {
      title: 'appName',
      dataIndex: 'appName',
    },
    {
      title: 'appLogo',
      dataIndex: 'appLogo',
      valueType: "image",
      hideInSearch: true,
    },
    {
      title: 'appId',
      dataIndex: 'appId',
      hideInSearch: true,
    },
    {
      title: '添加时间',
      dataIndex: 'createdDate',
      hideInSearch: true,
      width: 150,
      valueType: 'dateTime',
    },
    {
      title: '操作',
      dataIndex: 'opt',
      width: 110,
      valueType: 'option',
      render: (_, record) => [
        <Button
          key="edit"
          size="small"
          type="primary"
          icon={<EditOutlined />}
          onClick={() => {

          }}
        />,
        <Button
          key="remove"
          size="small"
          type="primary"
          danger
          icon={<DeleteFilled />}
          onClick={() => handleRemove(record.id)}
        />,
        <Button
          type="primary"
          key="setting"
          icon={<SettingOutlined />}
          onClick={() => {
            setValues(record);
            setSettingModalVisible(true);
          }}
        />,
      ],
    },
  ];

  return (
    <PageContainer title={false}>
      <ProTable<Record<string, any>, Record<string, any>>
        actionRef={actionRef}
        options={false}
        rowKey="id"
        bordered
        size="small"
        tableAlertRender={false}
        rowSelection={{
          selectedRowKeys,
          onChange: (selectedRowKeys) => setSelectedRowKeys(selectedRowKeys),
        }}
        toolBarRender={() => [
          <Button type="primary" key="add" onClick={() => actionRef.current?.reload()} icon={<ReloadOutlined />} />,
          <Button type="primary" key="add" onClick={() => {
            create().then(result=>{
              if(result.code===0){
                actionRef.current?.reload();
              }else{
                message.error(result.msg).then();
              }
            });
          }} icon={<PlusOutlined />} />,
          <Button
            disabled={selectedRowKeys.length === 0}
            type="primary"
            danger
            key="remove"
            icon={<DeleteFilled />}
            onClick={() => handleRemove()}
          />,
        ]}
        request={list}
        columns={columns}
      />

      {
        settingModalVisible && Object.keys(values).length>0 && values.id ? (
          <Index appId={values.id} onClose={()=>{
            setValues({});
            setSettingModalVisible(false);
            //history.push("/setting")
          }} />
        ) : null
      }
    </PageContainer>
  );
};
