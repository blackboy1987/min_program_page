import {ConfigProvider, Drawer, Image} from 'antd';
import React, {useEffect, useState} from 'react';
import {PageContainer, ProCard, ProConfigProvider, ProLayout,} from '@ant-design/pro-components';

import {detail} from '../../service';
import _defaultProps from "@/pages/app/components/Setting/_defaultProps";
import defaultProps from './_defaultProps';
import AccessToken from "./components/AccessToken";
import BaseSetting from "./components/BaseSetting";
import Income from "@/pages/app/components/Setting/components/Income";
import Member from "@/pages/app/components/Setting/components/Member";


type SettingProps = {
  appId: number;
  onClose: () => void;
};

const Index: React.FC<SettingProps> = ({appId,onClose}) => {
  const [pathname, setPathname] = useState('/list/sub-page/sub-sub-page1');
  const [appInfo,setAppInfo] = useState<Record<string, any>>({});
  useEffect(() => {
    detail({
      id:appId
    }).then(result=>{
      setAppInfo(result.data);
    })
  }, []);

  if (typeof document === 'undefined') {
    return <div />;
  }

  return (
    <Drawer
      styles={{
        body: {
          padding: 0, margin: 0
        }
      }}
      {..._defaultProps}
      width='100%'
      destroyOnClose
      maskClosable={false}
      open
      onClose={onClose}
      closeIcon={null}
    >
      <div
        id="test-pro-layout"
        style={{
          height: '100vh',
          overflow: 'auto',
        }}
      >
        <ProConfigProvider hashed={false}>
          <ConfigProvider
            getTargetContainer={() => {
              return document.getElementById('test-pro-layout') || document.body;
            }}
          >
            <ProLayout
              layout='mix'
              prefixCls="my-prefix"
              bgLayoutImgList={[
                {
                  src: 'https://img.alicdn.com/imgextra/i2/O1CN01O4etvp1DvpFLKfuWq_!!6000000000279-2-tps-609-606.png',
                  left: 85,
                  bottom: 100,
                  height: '303px',
                },
                {
                  src: 'https://img.alicdn.com/imgextra/i2/O1CN01O4etvp1DvpFLKfuWq_!!6000000000279-2-tps-609-606.png',
                  bottom: -68,
                  right: -45,
                  height: '303px',
                },
                {
                  src: 'https://img.alicdn.com/imgextra/i3/O1CN018NxReL1shX85Yz6Cx_!!6000000005798-2-tps-884-496.png',
                  bottom: 0,
                  left: 0,
                  width: '331px',
                },
              ]}
              {...defaultProps}
              location={{
                pathname,
              }}
              token={{
                header: {
                  colorBgMenuItemSelected: 'rgba(0,0,0,0.04)',
                },
              }}
              siderMenuType="group"
              menu={{
                collapsedShowGroupTitle: true,
              }}
              headerTitleRender={() => {
                return (
                  <a>
                    <Image preview={false} src={appInfo.appLogo} />
                    {appInfo.appName}
                  </a>
                );
              }}
              actionsRender={() => {
                return (
                  <div>
                    actionsRender
                  </div>
                )
              }}
              menuFooterRender={(props) => {
                if (props?.collapsed) return undefined;
                return (
                  <div
                    style={{
                      textAlign: 'center',
                      paddingBlockStart: 12,
                    }}
                  >
                    <div>© 2021 Made with love</div>
                    <div>by Ant Design</div>
                  </div>
                );
              }}
              onMenuHeaderClick={(e) => console.log(e)}
              menuItemRender={(item, dom) => (
                <div
                  onClick={() => {
                    console.log(item.path);
                    setPathname(item.path || '/welcome');
                  }}
                >
                  {dom}
                </div>
              )}
            >
              <PageContainer
                title={false}
                breadcrumb={{}}
                token={{
                  paddingInlinePageContainerContent: 8,
                  paddingBlockPageContainerContent:16,
                }}
              >
                <ProCard>
                  {
                    pathname==='/baseSetting' ? <BaseSetting appId={appInfo.id} /> : null
                  }
                  {
                    pathname==='/accessToken' ? <AccessToken appId={appInfo.id} /> : null
                  }
                  {
                    pathname==='/member' ? <Member appId={appInfo.id} /> : null
                  }
                  {
                    pathname==='/income' ? <Income appId={appInfo.id} /> : null
                  }
                </ProCard>
              </PageContainer>
            </ProLayout>
          </ConfigProvider>
        </ProConfigProvider>
      </div>
    </Drawer>
  );
};

export default Index;
