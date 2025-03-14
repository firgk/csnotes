

adb 技术





方法1：禁用App，这个方法相对安全一些


adb shell pm disable-user 软件包名
如果错误禁用了App，可以用下面的命令重新启用：

adb shell pm enable 软件包名



方法2：卸载App


有些App需要重复执行两次卸载命令才能卸载成功。

adb shell pm uninstall --user 0 软件包名
如果错误卸载了App，可以用下面的命令恢复，这个命令不是100%有效的，有一些被卸载App是无法用此命令恢复的。

adb shell pm install-existing --user 0 软件包名






如果上面的方法无效，备份手机内的重要数据，然后恢复手机出厂设置，恢复出厂后App会自动恢复。





在ADB查看已经禁用的程序
adb shell pm list packages -d









常见包名





| 应用名称 | 包名 |
| :--------------- | :------------------- |
| vivo TWS: 无线耳机 | com.android.vivo.tws.vivotws |
| 智能车载 | com.vivo.car.networking |
| vivo服务 | com.vivo.daemonService |
| 智慧引擎 | com.vivo.abe |
| i视频 | com.android.VideoPlayer |
| 电子书 | com.chaozh.iReader |
| i音乐 | com.android.bbkmusic |
| 互传 | com.vivo.easyshare |
| vivo 互传 | com.vivo.share |
| 推送引擎 | com.vivo.pushservice |
| vivo 游戏 | com.vivo.game |
| 游戏魔方 | com.vivo.gamecube |
| GameWatch | com.vivo.gamewatch |
| 钱包 | com.vivo.wallet |
| 官网 | com.vivo.space |
| 快应用 | com.vivo.hybrid |
| google play 服务 | com.google.android.gms |
| google play 服务更新 | com.android.vending |
| 全局搜索 | com.vivo.globalsearch |
| 快捷中心 | com.vivo.shortcutcenter |
| 浏览器 | com.vivo.browser |
| 用户体验改进计划服务 | com.bbk.iqoo.logsystem |
| 云服务 | com.bbk.cloud |
| 搜索挂件 | com.vivo.puresearch |


| 应用名称 | 包名 |
| :---------------------- | :------------------- |
| jovi智慧场景 | com.vivo.assistant |
| 全局搜索 | com.vivo.globalsearch |
| jovi收藏 | com.vivo.favorite |
| 语音唤醒 | com.vivo.voicewakeup |
| jovi语音 | com.vivo.agent |
| 系统升级 | com.bbk.updater |
| 智慧桌面 | com.vivo.hiboard |
| log信息采集 | com.android.bbklog |
| logsystem | com.bbk.iqoo.logsystem |
| aiservice | com.vivo.aiservice |
| 游戏中心 | com.vivo.game |
| 语音翻译 | com.vivo.aitranslate |
| 百度输入法 | com.baidu.input_vivo |
| vivo TWS: 无线耳机 | com.android.vivo.tws.vivotws |
| Google合作伙伴设置 | com.google.android.partnersetup |
| 悬浮球 | com.vivo.floatingball |
| 动作识别 | com.vivo.motionrecognition |
| 咖啡时间动态壁纸 | com.vivo.livewallpaper.coffeetime |
| 怪兽动态壁纸 | com.vivo.livewallpaper.monster |
| 快应用 | com.vivo.hybrid |
| 智慧桌面 | com.vivo.hiboard |
| 和多号 | com.vivo.heduohao |
| 全局动效 | com.vivo.globalanimation |
| 相册 | com.vivo.gallery |
| 语音翻译 | com.vivo.aitranslate |
| aiservice | com.vivo.aiservice |
| AI按键指南 | com.vivo.aikeyguide |
| 音效设置 | com.vivo.audiofx |
| 安全中心 | com.vivo.safecenter |
| 智慧引擎 | com.vivo.abe |
| i主题 | com.bbk.theme |
| 主题资源 | com.bbk.theme.resources |
| 百度输入法 | com.baidu.input_vivo |
| 百度地图定位 | com.baidu.map.location |
| 百度搜索框轻版 | com.baidu.searchbox.lite |
| Qualcomm测试模式 | com.qualcomm.qti.modemtestmode |
| vivo壁纸 | com.vlife.vivo.wallpaper |
| 圆角图标包（系统） | com.android.theme.icon_pack.rounded.android |
| 圆角图标包（启动器） | com.android.theme.icon_pack.rounded.launcher |
| 圆角图标包（设置） | com.android.theme.icon_pack.rounded.settings |
| 圆角图标包（系统UI） | com.android.theme.icon_pack.rounded.systemui |
| 填充图标包（系统） | com.android.theme.icon_pack.filled.android |
| 填充图标包（启动器） | com.android.theme.icon_pack.filled.launcher |
| 填充图标包（设置） | com.android.theme.icon_pack.filled.settings |
| 填充图标包（系统UI） | com.android.theme.icon_pack.filled.systemui |
| 填充图标包（主题选择器） | com.android.theme.icon_pack.filled.themepicker |


| 应用名称 | 包名 |
| :---------------------- | :------------------- |
| jovi智慧场景 | com.vivo.assistant |
| 全局搜索 | com.vivo.globalsearch |
| jovi收藏 | com.vivo.favorite |
| 语音唤醒 | com.vivo.voicewakeup |
| jovi语音 | com.vivo.agent |
| 系统升级 | com.bbk.updater |
| 智慧桌面 | com.vivo.hiboard |
| 智慧引擎 | com.vivo.abe |
| log信息采集 | com.android.bbklog |
| logsystem | com.bbk.iqoo.logsystem |
| aiservice | com.vivo.aiservice |
| 游戏中心 | com.vivo.game |
| 语音翻译 | com.vivo.aitranslate |
| 使用技巧 | com.android.tips |
| sim 卡工具 | com.android.stk |






ai 去重
以下是去重后的应用包名列表：


| 应用名称 | 包名 |
| :---------------------- | :------------------- |
| vivo TWS: 无线耳机 | com.android.vivo.tws.vivotws |
| 智能车载 | com.vivo.car.networking |
| vivo服务 | com.vivo.daemonService |
| 智慧引擎 | com.vivo.abe |
| i视频 | com.android.VideoPlayer |
| 电子书 | com.chaozh.iReader |
| i音乐 | com.android.bbkmusic |
| 互传 | com.vivo.easyshare |
| vivo 互传 | com.vivo.share |
| 推送引擎 | com.vivo.pushservice |
| vivo 游戏 | com.vivo.game |
| 游戏魔方 | com.vivo.gamecube |
| GameWatch | com.vivo.gamewatch |
| 钱包 | com.vivo.wallet |
| 官网 | com.vivo.space |
| 快应用 | com.vivo.hybrid |
| google play 服务 | com.google.android.gms |
| google play 服务更新 | com.android.vending |
| 全局搜索 | com.vivo.globalsearch |
| 快捷中心 | com.vivo.shortcutcenter |
| 浏览器 | com.vivo.browser |
| 用户体验改进计划服务 | com.bbk.iqoo.logsystem |
| 云服务 | com.bbk.cloud |
| 搜索挂件 | com.vivo.puresearch |
| jovi智慧场景 | com.vivo.assistant |
| jovi收藏 | com.vivo.favorite |
| 语音唤醒 | com.vivo.voicewakeup |
| jovi语音 | com.vivo.agent |
| 系统升级 | com.bbk.updater |
| 智慧桌面 | com.vivo.hiboard |
| log信息采集 | com.android.bbklog |
| aiservice | com.vivo.aiservice |
| 游戏中心 | com.vivo.game |
| 语音翻译 | com.vivo.aitranslate |
| 百度输入法 | com.baidu.input_vivo |
| Google合作伙伴设置 | com.google.android.partnersetup |
| 悬浮球 | com.vivo.floatingball |
| 动作识别 | com.vivo.motionrecognition |
| 咖啡时间动态壁纸 | com.vivo.livewallpaper.coffeetime |
| 怪兽动态壁纸 | com.vivo.livewallpaper.monster |
| 和多号 | com.vivo.heduohao |
| 全局动效 | com.vivo.globalanimation |
| 相册 | com.vivo.gallery |
| AI按键指南 | com.vivo.aikeyguide |
| 音效设置 | com.vivo.audiofx |
| 安全中心 | com.vivo.safecenter |
| i主题 | com.bbk.theme |
| 主题资源 | com.bbk.theme.resources |
| 百度地图定位 | com.baidu.map.location |
| 百度搜索框轻版 | com.baidu.searchbox.lite |
| Qualcomm测试模式 | com.qualcomm.qti.modemtestmode |
| vivo壁纸 | com.vlife.vivo.wallpaper |
| 圆角图标包（系统） | com.android.theme.icon_pack.rounded.android |
| 圆角图标包（启动器） | com.android.theme.icon_pack.rounded.launcher |
| 圆角图标包（设置） | com.android.theme.icon_pack.rounded.settings |
| 圆角图标包（系统UI） | com.android.theme.icon_pack.rounded.systemui |
| 填充图标包（系统） | com.android.theme.icon_pack.filled.android |
| 填充图标包（启动器） | com.android.theme.icon_pack.filled.launcher |
| 填充图标包（设置） | com.android.theme.icon_pack.filled.settings |
| 填充图标包（系统UI） | com.android.theme.icon_pack.filled.systemui |
| 填充图标包（主题选择器） | com.android.theme.icon_pack.filled.themepicker |
| 使用技巧 | com.android.tips |
| sim 卡工具 | com.android.stk |



