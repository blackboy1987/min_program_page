import type {ActionType, ProColumns} from '@ant-design/pro-components';
import {PageContainer, ProTable,} from '@ant-design/pro-components';
import {Button, Tag, Tooltip} from 'antd';
import React, {useRef} from 'react';
import {list} from "./service";

type AccessTokenProps = {
  appId: number;
}

const AccessToken: React.FC<AccessTokenProps> = ({appId}) => {

  const actionRef = useRef<ActionType>();

  const columns: ProColumns<Record<string, any>>[] = [
    {
      title: "昵称",
      dataIndex: 'nickName',
    },
    {
      title: "头像",
      valueType:'avatar',
      width:80,
      dataIndex: 'avatarUrl',
    },
    {
      title: "是否认证",
      width:80,
      dataIndex: 'isAuth',
      valueEnum:{
        true:{
          text:'是'
        },
        false:{
          text: '否'
        }
      }
    },
    {
      title: "注册时间",
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
              actionRef.current?.reload();
            }}
          >
            刷新
          </Button>,
        ]}
        request={list}
        columns={columns}
      />
    </PageContainer>
  );
};

export default AccessToken;
