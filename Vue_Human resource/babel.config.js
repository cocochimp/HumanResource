// 这是项目发布阶段需要用到的 babel 插件
const prodPlugins = []
    // 判断当前为开发阶段 development 还是发布阶段  production
if (process.env.NODE_ENV === 'production') {
    prodPlugins.push('transform-remove-console')
}

module.exports = {
    presets: ['@vue/app'],
    plugins: [
        [
            'component',
            {
                libraryName: 'element-ui',
                styleLibraryName: 'theme-chalk'
            }
        ],
        // 发布产品时候的插件数组,...表示展开运算符，意思是将数组里的每一项展开
        ...prodPlugins,
        // "transform-remove-console",
        '@babel/plugin-syntax-dynamic-import'
    ]
}