#####1. Development considerations

As you develop your library modules and dependent apps, be aware of the following behaviors and limitations.

Once you have added references to library modules to your Android app module, you can set their relative priority. At build time, the libraries are merged with the app one at a time, starting from the lowest priority to the highest.

Resource merge conflicts
The build tools merge resources from a library module with those of a dependent app module. If a given resource ID is defined in both modules, the resource from the app is used.
If conflicts occur between multiple AAR libraries, then the resource from the library listed first in the dependencies list (toward the top of the dependencies block) is used.
To avoid resource conflicts for common resource IDs, consider using a prefix or other consistent naming scheme that is unique to the module (or is unique across all project modules).

#####2. Merge duplicate resources

By default, Gradle also merges identically named resources, such as drawables with the same name that might be in different resource folders. This behavior is not controlled by the shrinkResources property and cannot be disabled, because it is necessary to avoid errors when multiple resources match the name your code is looking up.

Resource merging occurs only when two or more files share an identical resource name, type, and qualifier. Gradle selects which file it considers to be the best choice among the duplicates (based on a priority order described below) and passes only that one resource to the AAPT for distribution in the APK file.

Gradle looks for duplicate resources in the following locations:

The main resources, associated with the main source set, generally located in src/main/res/.
The variant overlays, from the build type and build flavors.
The library project dependencies.

Gradle merges duplicate resources in the following cascading priority order:

Dependencies → Main → Build flavor → Build type

For example, if a duplicate resource appears in both your main resources and a build flavor, Gradle selects the one in the build flavor.

If identical resources appear in the same source set, Gradle cannot merge them and emits a resource merge error. This can happen if you define multiple source sets in the sourceSet property of your build.gradle file—for example if both src/main/res/ and src/main/res2/ contain identical resources.

#####3. Installation .apk via File Manager(Total Commander)
- add files .apk's to path-to-sdk/platforms-tools dir
- push files to device in Android Device Monitor(ADM)
- install .apk's on device via installed File Manager

#####4. About differences API22 vs API23 (Android 5.1 vs 6.0)

On all versions of Android, your app needs to declare both the normal and the dangerous permissions it needs in its app manifest, as described in Declaring Permissions. However, the effect of that declaration is different depending on the system version and your app's target SDK level:

If the device is running Android 5.1 or lower, or your app's target SDK is 22 or lower: If you list a dangerous permission in your manifest, the user has to grant the permission when they install the app; if they do not grant the permission, the system does not install the app at all.
If the device is running Android 6.0 or higher, and your app's target SDK is 23 or higher: The app has to list the permissions in the manifest, and it must request each dangerous permission it needs while the app is running. The user can grant or deny each permission, and the app can continue to run with limited capabilities even if the user denies a permission request.

Note: Beginning with Android 6.0 (API level 23), users can revoke permissions from any app at any time, even if the app targets a lower API level. You should test your app to verify that it behaves properly when it's missing a needed permission, regardless of what API level your app targets. 