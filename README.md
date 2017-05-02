1. Development considerations

As you develop your library modules and dependent apps, be aware of the following behaviors and limitations.

Once you have added references to library modules to your Android app module, you can set their relative priority. At build time, the libraries are merged with the app one at a time, starting from the lowest priority to the highest.

Resource merge conflicts
The build tools merge resources from a library module with those of a dependent app module. If a given resource ID is defined in both modules, the resource from the app is used.
If conflicts occur between multiple AAR libraries, then the resource from the library listed first in the dependencies list (toward the top of the dependencies block) is used.
To avoid resource conflicts for common resource IDs, consider using a prefix or other consistent naming scheme that is unique to the module (or is unique across all project modules).

2. Merge duplicate resources

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
