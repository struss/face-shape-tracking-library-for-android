# Introduction #

It extends fstLibraryBase and it connects to JNI functions which is about Active Shape Model.

# Details #

Add your content here.  Format your content with:
  * Text in **bold** or _italic_
  * Headings, paragraphs, and lists
  * Automatic links to other wiki pages


# Public Method Overview #

**public fstLibrary(Context context, int cIdx)**

  * @param context - android.content.Context
    * @param cIdx
    * - Camera Index
    * - fstLibraryBase.BACK\_CAMERA  - (0)
    * - fstLibraryBase.FRONT\_CAMERA - (1)

  * <b>Usage</b>
    * fstLibrary mFstLibrary = fstLibrary(this, fstLibraryBase.FRONT\_CAMERA);
    * frameLayout.addView(mFstLibrary);
> > > OR
    * setContentView(mFstLibrary);