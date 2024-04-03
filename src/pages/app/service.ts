import {request} from '@umijs/max';
import {Constants} from '@/util/constants';

export async function list(body: Record<string, any>, options?: { [key: string]: any }) {
  return request(Constants.apiUrl + 'app/list', {
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

export async function remove(body: Record<string, any>, options?: { [key: string]: any }) {
  return request(Constants.apiUrl + 'app/delete', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}
export async function create(options?: { [key: string]: any }) {
  return request(Constants.apiUrl + 'app/create', {
    method: 'POST',
    ...(options || {}),
  });
}

export async function detail(body: Record<string, any>, options?: { [key: string]: any }) {
  return request(Constants.apiUrl + 'app/detail', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}
