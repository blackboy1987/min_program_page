import {request} from '@umijs/max';
import {Constants} from '@/util/constants';

export async function list(body: Record<string, any>, options?: { [key: string]: any }) {
  return request(Constants.baseUrl + 'token/list', {
    method: 'POST',
    data: {
      ...body,
      pageNumber: body.current || 1,
    },
    ...(options || {}),
  }).then((result) => {
    return {
      success: true,
      data: result.data.content,
      total: result.data.total,
    };
  });
}

export async function pull(body: Record<string, any>, options?: { [key: string]: any }) {
  return request(Constants.baseUrl + 'token/pull', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}
