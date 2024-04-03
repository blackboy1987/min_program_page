import {Button, message, Upload, UploadProps} from "antd";
import {UploadOutlined} from "@ant-design/icons";
import {Constants} from "@/util/constants";

const ImageUpload = () =>{

  const props: UploadProps = {
    name: 'file',
    action: Constants.uploadUrl,
    headers: {
      authorization: 'authorization-text',
    },
    onChange(info) {
      if (info.file.status !== 'uploading') {
        console.log(info.file, info.fileList);
      }
      if (info.file.status === 'done') {
        message.success(`${info.file.name} file uploaded successfully`).then();
      } else if (info.file.status === 'error') {
        message.error(`${info.file.name} file upload failed.`).then();
      }
    },
  };

  return (
    <Upload {...props}>
      <Button icon={<UploadOutlined />} type='primary' style={{borderRadius:0,boxSizing:'inherit'}}>
        aaa
      </Button>
    </Upload>

  )
}

export default ImageUpload;
