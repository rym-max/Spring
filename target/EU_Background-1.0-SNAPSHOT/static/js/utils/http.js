axios.interceptors.request.use(config => {
    // loading
    //layui.use('layer', function () {
    //    var layer = layui.layer;
    //    layer.load();
    //});
    return config
}, error => {
    return Promise.reject(error)
})

axios.interceptors.response.use(response => {
    return response
}, error => {
    return Promise.resolve(error.response)
})

function checkStatus(response) {
    // loading
    // 如果http状态码正常，则直接返回数据
    if (response && (response.status === 200 || response.status === 304 || response.status === 400)) {
        return response.data
        // 如果不需要除了data之外的数据，可以直接 return response.data
    }
    // 异常状态下，把错误信息返回去
    return {
        status: -404,
        code: response.status,
        msg: '网络异常'
    }
}

function checkCode(res) {
    // 如果code异常(这里已经包括网络错误，服务器错误，后端抛出的错误)，可以弹出一个错误提示，告诉用户
    if (res.status === -404) {
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.msg("服务器繁忙，请稍后在试！", { icon: 2 });
        });
        return false;
    }
    //if (res.data && (!res.data.success)) {
    //    alert(res.data.error_msg)
    //}
    //layui.use('layer', function () {
    //    var layer = layui.layer;
    //    layer.closeAll();
    //});
    return res
}

var http = {
    post(url, data/*data*/) {
        return axios({
            method: 'post',
            //baseURL: 'http://localhost:26714',
            url,
            //data: qs.stringify(data),
            data,
            timeout: 10000,
            headers: {
                'X-Requested-With': 'XMLHttpRequest',
                'Content-Type': 'application/json; charset=UTF-8'
            }
        }).then(
            (response) => {
                return checkStatus(response)
            }
            ).then(
            (res) => {
                return checkCode(res)
            }
            )
    },
    get(url, params) {
        return axios({
            method: 'get',
            //baseURL: 'http://localhost:26714',
            url,
            params, // get 请求时带的参数
            timeout: 10000,
            headers: {
                'X-Requested-With': 'XMLHttpRequest'
            }
        }).then(
            (response) => {
                return checkStatus(response)
            }
            ).then(
            (res) => {
                return checkCode(res)
            }
            )
    },
    post2(url, data/*data*/) {
        return axios({
            method: 'post',
            //baseURL: 'http://localhost:26714',
            url,
            //data: qs.stringify(data),
            data,
            timeout: 10000,
            headers: {
                'X-Requested-With': 'XMLHttpRequest',
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(
            (response) => {
                return checkStatus(response)
            }
        ).then(
            (res) => {
                return checkCode(res)
            }
        )
    }
}