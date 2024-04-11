let url = 'http://localhost:9992/admin/api/';
const isDev = process.env.NODE_ENV === 'development';
if (!isDev) {
  url = '/';
}
export const Constants = {
  baseUrl: `${url}`,
  uploadUrl: `${url}file/upload`,
};
