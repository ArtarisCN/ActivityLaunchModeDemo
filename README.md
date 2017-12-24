
## Activity 的加载模式

Activity 的生命周期和加载模式是 Android 开发的基础，有些项目中的需求可以巧妙使用不同的加载模式来解决，之前使用 Android 加载模式时还有一些没有理解透彻的地方，在此做一个总结。此外，当打开已存在栈中的 Activity 时，并不会走`onCreate()`方法，而是会走`onNewIntent()`方法，在`onCreate()`做过的操作也会一并走一遍，在此也对这种情况做一个说明。

<!-- more -->

项目地址：[AcitivityLaunchModeDemo](https://github.com/ArtarisCN/ActivityLaunchModeDemo)

Activity 的加载模式是在`AndroidManifest.xml`中设置的，具体如下：
```
<activity android:name=".ui.activity.MainActivity"
  	  android:launchMode="singleTask"/>
```
#### Standard（默认）
如果不指定`launchMode`，则默认为`standard`。
当启动模式设置成`standard`时，每次生成新的 Activity ，就会在当前任务栈中生成新的页面。当打开一个新的页面时，其生命周期如下

![](http://img.artaris.cn/activity_launch_model/stander_1.png)

```
On Create
On Start
On Resume
```
当再次开启一个新的`standard`页面时，前一个Activity 的生命周期如下

![](http://img.artaris.cn/activity_launch_model/stander_2.png)

```
On Pause
On Stop
```

新开的页面继续重复其生命周期
```
On Create
On Start
On Resume
```
当关闭屏幕时，其生命周期如下

![](http://img.artaris.cn/activity_launch_model/stander_6.png)


```
On Pause

On Stop
```
重新开启屏幕时，其生命周期如下

![](http://img.artaris.cn/activity_launch_model/stander_3.png)

```
On Restart
On Start
On Resume
```
当前任务栈如下

![](http://img.artaris.cn/activity_launch_model/stander_4.jpg?imageMogr2/thumbnail/!30p)

#### SingleTop

`singleTop`其实和`standard`几乎一样，使用`singleTop`的 Activity 也可以创建很多个实例。唯一不同的就是，如果调用的目标 Activity 已经位于调用者的Task的栈顶，则不创建新实例，而是使用当前的这个 Activity 实例，并调用这个实例的 `onNewIntent()`方法。如果是外部程序跨应用启动singleTop的Activity，在Android 5.0之前新创建的Activity会位于调用者的Task中，5.0及以后会放入新的Task中。

当当前栈顶页面不是`singleTop`时，其效果与`standard`一样的，在此不再赘述。当前页面已经是`singleTop`，会使用当前实例，点击新开`singleTop`其生命周期如下

![](http://img.artaris.cn/activity_launch_model/single_top_5.png)

```
On Pause
On New Intent
On Resume
```

可以看到，新开的页面并没有重新创建，而是调用了顶端 Activity 的`onNewIntent()`方法。

#### SingleTask

- 在同一程序时
`singleTask`简单来说，就是当前栈中只有一个该页面的实例。使用`singleTask`创建新的 Activity 时会先检查当前栈中是否有此实例，如果有这个实例，则会将此实例之上所有的 Activity 统统出栈，使此Activity实例成为栈顶对象，显示到幕前，然后调用 `onNewIntent()`方法。
调用前栈内信息如下：

![](http://img.artaris.cn/activity_launch_model/single_task_1.jpg?imageMogr2/thumbnail/!30p)

可以看到当前`singleTask`页面为第三个页面（其上还有两个`standard`页面），这时我们点击生成`singleTask`页面，任务栈变化如下

![](http://img.artaris.cn/activity_launch_model/single_task_2.jpg?imageMogr2/thumbnail/!30p)

在栈中的`singleTask`的生命周期变化如下

![](http://img.artaris.cn/activity_launch_model/single_task_3.png)

```
On New Intent
On Restart
On Start
On Resume
```
- 不同程序之间
1. 在跨应用Intent传递时，如果系统中不存在singleTask Activity的实例，那么将创建一个新Task，然后创建SingleTask Activity实例，将其放入新的Task中。
2. 如果singleTask Activity所在的应用进程存在，但是singleTask Activity实例不存在，那么从别的应用启动这个Activity，新的Activity实例会被创建，并放入到所属进程所在的Task中，并位于栈顶位置。
3. 更复杂的一种情况，如果singleTask Activity实例存在，从其他程序被启动，那么这个Activity所在的Task会被移到顶部，并且在这个Task中，位于singleTask Activity实例之上的所有Activity将会被正常销毁掉。如果我们按返回键，那么我们首先会回退到这个Task中的其他Activity，直到当前Task的Activity回退栈为空时，才会返回到调用者的Task。

#### SingleInstance

`singleInstance`模式下无论在任何任务栈启动，全局都只会有一个 `singleInstance` 实例。如果在`singleInstance` 启动其他模式的页面，那么这个其他页面的实例会放入其他任务栈中。就是说`singleInstance`会在一个单独的全新的任务栈中。

当从其他页面跳到`singleInstance`时重新启用了一个新的栈结构，来放置`singleInstance`实例，然后按下后退键，再次回到原始栈结构；
如果在`singleInstance`页面新开一个`standard` 页面，则会在原栈中生成一个页面，`singleInstance`依然存在，当在原栈中点击返回两次，则会关闭原栈，页面会切到`singleInstance`页面。
