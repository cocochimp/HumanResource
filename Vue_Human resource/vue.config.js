const { defineConfig } = require('@vue/cli-service')

module.exports = {
    chainWebpack: config => {
        //  使用chainWebpack节点修改webpack默认的配置，当处于生产环境，获取默认的打包入口（main.js），清除，再通过add方法添加指定的入口文件
        config.when(process.env.NODE_ENV === 'production', config => {
                config.entry('app').clear().add('./src/main-prod.js')
                    // 在externals节点中指定不被合并到打包后生成的文件的依赖，之后使用import导入的指定依赖的js文件就不会被合并到打包生成的文件中了
                config.set('externals', {
                        vue: 'Vue',
                        'vue-router': 'VueRouter',
                        axios: 'axios',
                        lodash: '_',
                        echarts: 'echarts',
                        nprogress: 'NProgress',
                        'vue-quill-editor': 'VueQuillEditor'
                    })
                    //  发布模式引入cdn资源
                config.plugin('html').tap(args => {
                    args[0].isProd = true
                    return args
                })

            })
            // 当处于开发环境，获取默认的打包入口（main.js），清除，再通过add方法添加指定的入口文件
        config.when(process.env.NODE_ENV === 'development', config => {
            config.entry('app').clear().add('./src/main-dev.js')
                // 开发模式不引入cdn资源
            config.plugin('html').tap(args => {
                args[0].isProd = false
                return args
            })
        })
    }
}