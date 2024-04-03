import type {ActionType, ProColumns} from '@ant-design/pro-components';
import {PageContainer, ProTable,} from '@ant-design/pro-components';
import {Button, Tag, Tooltip} from 'antd';
import React, {useRef} from 'react';
import {list,pull} from "./service";

type AccessTokenProps = {
  appId: number;
}

const AccessToken: React.FC<AccessTokenProps> = ({appId}) => {

  const actionRef = useRef<ActionType>();

  const columns: ProColumns<Record<string, any>>[] = [
    {
      title: "token",
      dataIndex: 'accessToken',
      renderText:(text)=>(
        <Tooltip title='为了安全，只显示部分数据'>
          <Tag>{text}</Tag>
        </Tooltip>
      )
    },
    {
      title: "过期时间",
      valueType:'dateTime',
      width:150,
      dataIndex: 'expireDate',
    },
    {
      title: "生成时间",
      valueType:'dateTime',
      width:150,
      dataIndex: 'createdDate',
    },
  ];

  return (
    <PageContainer title={false}>
      <ProTable<Record<string, any>, Record<string, any>>
        actionRef={actionRef}
        rowKey="id"
        cardProps={false}
        search={false}
        options={false}
        bordered={false}
        size='small'
        params={{appId}}
        toolBarRender={() => [
          <Button
            type="primary"
            key="primary"
            onClick={() => {
              pull({appId}).then(result=>{
                console.log(result);
                actionRef.current?.reload();
              })
            }}
          >
            重新获取
          </Button>,
        ]}
        request={list}
        columns={columns}
      />
    </PageContainer>
  );
};

export default AccessToken;
