import {request} from '@umijs/max';
import {Constants} from '@/util/constants';

export async function list(body: Record<string, any>, options?: { [key: string]: any }) {
  return request(Constants.baseUrl + 'app/list', {
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
  return request(Constants.baseUrl + 'app/delete', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}
export async function create(options?: { [key: string]: any }) {
  return request(Constants.baseUrl + 'app/create', {
    method: 'POST',
    ...(options || {}),
  });
}

export async function detail(body: Record<string, any>, options?: { [key: string]: any }) {
  return request(Constants.baseUrl + 'app/detail', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}


export async function base(body: Record<string, any>,options?: Record<string, any>) {
  return request<Record<string, any>>(`${Constants.baseUrl}app/base`, {
    method: 'POST',
    requestType:'form',
    data: body,
    ...(options || {}),
  });
}

export async function baseUpdate(body: Record<string, any>) {
  return request<Record<string, any>>(`${Constants.baseUrl}app/baseUpdate`, {
    method: 'POST',
    requestType:'form',
    data: body,
  });
}
