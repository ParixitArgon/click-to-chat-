package com.example.clicktooprnchat.ui

import AppPreferences
import android.app.Dialog
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.content.res.Configuration
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.ScrollView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.clicktooprnchat.R
import com.example.clicktooprnchat.adapter.PhoneNumberAdapter
import com.example.clicktooprnchat.databinding.ActivityTransparentBinding
import com.example.clicktooprnchat.databinding.CustomPopupLayoutBinding
import com.example.clicktooprnchat.databinding.CustomPopupLlLayoutBinding
import com.example.clicktooprnchat.databinding.CustomSettingPopupLayoutBinding
import com.example.clicktooprnchat.databinding.DeleteDilogPopupLayoutBinding
import com.example.clicktooprnchat.databinding.SavedContectListPopupLayoutBinding
import com.google.i18n.phonenumbers.PhoneNumberUtil
import java.net.URLEncoder

class TransparentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransparentBinding
    private lateinit var customDialogBinding: CustomPopupLlLayoutBinding
    private lateinit var savedContectListPopupLayoutBinding: SavedContectListPopupLayoutBinding
    private lateinit var deleteDilogPopupLayoutBinding: DeleteDilogPopupLayoutBinding
    private lateinit var customSettingPopupLayoutBinding: CustomSettingPopupLayoutBinding

    private var customDialog: Dialog? = null
    private var customSaveContetctDialog: Dialog? = null
    private var settingDialog: Dialog? = null
    private var deleteDialog: Dialog? = null
    private var customSettinDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransparentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showCustomDialog()
    }

    private fun showCustomDialog() {

        val appPreferences = AppPreferences(this)

        if (!isFinishing) {


            customDialog = Dialog(this)
            customDialogBinding = CustomPopupLlLayoutBinding.inflate(layoutInflater)
            customDialog?.setContentView(customDialogBinding.root)
            customDialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

            val displayMetrics = resources.displayMetrics
            val width = (displayMetrics.widthPixels * 0.9).toInt()
//            val height = (displayMetrics.widthPixels * 1.2).toInt()
            val height = WindowManager.LayoutParams.WRAP_CONTENT

            customDialog?.window?.setLayout(width, height)


            val completeText =
                getString(R.string.this_app_use_whatsapp_public_api_to_open_a_chat_with_any_number_you_enter_no_contact_is_created_on_the_device_n_more_info_here)
            val spannableString = SpannableString(completeText)
            val hereText = "here"
            val hereTextStartIndex = completeText.indexOf(hereText)
            val hereTextEndIndex = hereTextStartIndex + hereText.length
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    // Handle the click event here, e.g., open a browser
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://faq.whatsapp.com/general/chats/how-to-use-click-to-chat?lang=en-US")
                    )
                    startActivity(intent)
                }
            }
            spannableString.setSpan(
                clickableSpan,
                hereTextStartIndex,
                hereTextEndIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableString.setSpan(
                android.text.style.ForegroundColorSpan(resources.getColor(R.color.main_green)),
                hereTextStartIndex,
                hereTextEndIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableString.setSpan(
                android.text.style.UnderlineSpan(),
                hereTextStartIndex,
                hereTextEndIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            customDialogBinding.textView2.text = spannableString
            customDialogBinding.textView2.movementMethod = LinkMovementMethod.getInstance()


            val completeText1 =
                getString(R.string.insert_the_number_and_the_and_the_prefix_with_no_extra_characters_only_numbers_0_9_more_info_here_you_can_click_on_the_territory_button_to_choose_from_a_list_nexample_for_44_uk_123_456_7890_write_441234567890)
            val spannableString1 = SpannableString(completeText1)
            val prefixText = "prefix"
            val hereText1 = "here"
            val prefixTextStartIndex = completeText1.indexOf(prefixText)
            val prefixTextEndIndex = prefixTextStartIndex + prefixText.length
            val hereTextStartIndex1 = completeText1.indexOf(hereText1)
            val hereTextEndIndex1 = hereTextStartIndex1 + hereText1.length

            val prefixClickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://en.m.wikipedia.org/wiki/List_of_country_calling_codes#Alphabetical_listing_by_country_or_region")
                    )
                    startActivity(intent)
                }
            }
            val hereClickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://faq.whatsapp.com/general/about-international-phone-number-format?lang=en-US")
                    )
                    startActivity(intent)
                }
            }

            spannableString1.setSpan(
                prefixClickableSpan,
                prefixTextStartIndex,
                prefixTextEndIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableString1.setSpan(
                hereClickableSpan,
                hereTextStartIndex1,
                hereTextEndIndex1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableString1.setSpan(
                android.text.style.ForegroundColorSpan(resources.getColor(R.color.main_green)),
                prefixTextStartIndex,
                prefixTextEndIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableString1.setSpan(
                android.text.style.UnderlineSpan(),
                prefixTextStartIndex,
                prefixTextEndIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableString1.setSpan(
                android.text.style.ForegroundColorSpan(resources.getColor(R.color.main_green)),
                hereTextStartIndex1,
                hereTextEndIndex1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableString1.setSpan(
                android.text.style.UnderlineSpan(),
                hereTextStartIndex1,
                hereTextEndIndex1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            customDialogBinding.textView3.text = spannableString1
            customDialogBinding.textView3.movementMethod = LinkMovementMethod.getInstance()



            if (customDialogBinding.editTextCarrierNumber.text.isNullOrEmpty()) {
                disableButtons()
            } else {
                enableButtons()
            }

            if (!appPreferences.getRestorePrefix()) {
                val countryCodeString = appPreferences.getCountryCode().toString()
                if (countryCodeString.isNotEmpty()) {
                    val countryCodeInt = countryCodeString.toInt()
                    customDialogBinding.ccp.setCountryForPhoneCode(countryCodeInt)
                    Log.i("TAG", "Setting country code: $countryCodeInt")
                }
            }

            if (appPreferences.getHideHelpTextFromMain()) {
                customDialogBinding.conHelpText.visibility = View.GONE
                customDialogBinding.txHelpText.visibility = View.GONE
            }

            customDialogBinding.ivSetting.setOnClickListener {
                openSettingDialog()
            }

            customDialogBinding.btDownArrow.setOnClickListener {
                if (customDialogBinding.glExtraButtons.visibility == View.VISIBLE) {

                    customDialogBinding.scrollView2.post {
                        customDialogBinding.scrollView2.smoothScrollTo(
                            0,
                            customDialogBinding.scrollView2.bottom
                        )
                    }

                    customDialogBinding.glExtraButtons.visibility = View.GONE
                    customDialogBinding.llMainMessageLayout.visibility = View.GONE
                    customDialogBinding.btDownArrow.setImageResource(R.drawable.ic_drop_down_white)
                } else {

                    customDialogBinding.scrollView2.post {
                        customDialogBinding.scrollView2.smoothScrollTo(
                            0,
                            customDialogBinding.scrollView2.bottom
                        )
                    }

                    customDialogBinding.glExtraButtons.visibility = View.VISIBLE
                    customDialogBinding.llMainMessageLayout.visibility = View.VISIBLE
                    customDialogBinding.btDownArrow.setImageResource(R.drawable.ic_arrow_drop_white)
                }
            }

            customDialogBinding.ivInfo.setOnClickListener {
//                openInfoDialog()
            }

            customDialog?.setOnDismissListener {
                finish()
            }

            customDialogBinding.clearText?.setOnClickListener {
                customDialogBinding.editTextCarrierNumber.text = null
            }

            customDialogBinding.editTextCarrierNumber.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {
                    // Not used in this example
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Not used in this example
                }

                override fun afterTextChanged(s: Editable?) {
                    // Remove leading zeros using the extension function and update the EditText
                    if (appPreferences.getRemoveLeadingZeros()) {
                        val input = s.toString()
                        val result = input.removeLeadingZeros()
                        if (input != result) {
                            customDialogBinding.editTextCarrierNumber.setText(result)
                            customDialogBinding.editTextCarrierNumber.setSelection(result.length) // Move the cursor to the end
                        }
                    }


                    if (s.isNullOrEmpty()) {
                        disableButtons()
                    } else {
                        enableButtons()
                    }
                }
            })


            customDialogBinding.btnOpen.setOnClickListener {
                if (!customDialogBinding.editTextCarrierNumber.text.isNullOrEmpty()) {
                    val phoneNumber = getFormattedPhoneNumber()
                    val countryCode = customDialogBinding.ccp?.selectedCountryCodeWithPlus
                    val message = customDialogBinding.etMessage.text.toString()

                    Log.i("TAG", "showCustomDialog: " + countryCode)


                    Log.i("TAG", "showCustomDialog: " + appPreferences.getSaveRecentAndMessages())
                    Log.i("TAG", "showCustomDialog: " + appPreferences.getPhoneNumbers())
                    if (appPreferences.getSaveRecentAndMessages()) {
                        appPreferences.addPhoneNumber(phoneNumber)
                        appPreferences.setCountryCode(countryCode)
                    }

                    if (customDialogBinding.etMessage.text.isNullOrEmpty()) {
                        openWhatsAppChat(phoneNumber)
                    } else {
                        openWhatsAppChatWithChat(phoneNumber, message)
                    }
                }
            }

            customDialogBinding.btnCall.setOnClickListener {
                if (!customDialogBinding.editTextCarrierNumber.text.isNullOrEmpty()) {
                    val phoneNumber = getFormattedPhoneNumber()
                    makePhoneCall(phoneNumber)
                }
            }

            customDialogBinding.btnShare.setOnClickListener {
                if (!customDialogBinding.editTextCarrierNumber.text.isNullOrEmpty()) {
                    val phoneNumber = getFormattedPhoneNumber()
                    shareLink(phoneNumber)
                }
            }

            customDialogBinding.btnSms.setOnClickListener {
                if (!customDialogBinding.editTextCarrierNumber.text.isNullOrEmpty()) {
                    val phoneNumber = getFormattedPhoneNumber()
                    val message = customDialogBinding.etMessage.text.toString()
                    openSmsWithMessage(phoneNumber, message)
                }
            }

            customDialogBinding.btnShortcut.setOnClickListener {
                if (!customDialogBinding.editTextCarrierNumber.text.isNullOrEmpty()) {
                    val phoneNumber = getFormattedPhoneNumber()

                    val message = customDialogBinding.etMessage.text.toString()

                    if (customDialogBinding.etMessage.text.isNullOrEmpty()) {
                        createWhatsAppChatShortcut(phoneNumber)
                    } else {
                        createWhatsAppChatWithMessageShortcut(phoneNumber, message)
                    }
                }
            }

            customDialogBinding.ivList.setOnClickListener {
                if (!appPreferences.getPhoneNumbers().toList()
                        .isNullOrEmpty() || !appPreferences.getPinnedPhoneNumbers().toList()
                        .isNullOrEmpty()
                ) {
                    openSaveNumberDialog()
                } else {
                    Toast.makeText(
                        this@TransparentActivity,
                        "there is not data to show",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            customDialogBinding.ivPast.setOnClickListener {
                val lastCopiedText = getLastCopiedText()
                customDialogBinding.editTextCarrierNumber.setText(lastCopiedText)
            }

            customDialog?.window?.decorView?.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_OUTSIDE) {
                    return@setOnTouchListener true
                }
                false
            }

            customDialog?.show()
        }

    }

    private fun disableButtons() {
        val colorStateList = ContextCompat.getColorStateList(this, R.color.button_colors)
        customDialogBinding.btnOpen.apply {
            isEnabled = false
            backgroundTintList = colorStateList
        }
        customDialogBinding.btnCall.apply {
            isEnabled = false
            backgroundTintList = colorStateList
        }
        customDialogBinding.btnShare.apply {
            isEnabled = false
            backgroundTintList = colorStateList
        }
        customDialogBinding.btnSms.apply {
            isEnabled = false
            backgroundTintList = colorStateList
        }
        customDialogBinding.btnShortcut.apply {
            isEnabled = false
            backgroundTintList = colorStateList
        }
    }

    private fun enableButtons() {
        val colorStateList = ContextCompat.getColorStateList(this, R.color.button_colors)
        customDialogBinding.btnOpen.apply {
            isEnabled = true
            backgroundTintList = colorStateList
        }
        customDialogBinding.btnCall.apply {
            isEnabled = true
            backgroundTintList = colorStateList
        }
        customDialogBinding.btnShare.apply {
            isEnabled = true
            backgroundTintList = colorStateList
        }
        customDialogBinding.btnSms.apply {
            isEnabled = true
            backgroundTintList = colorStateList
        }
        customDialogBinding.btnShortcut.apply {
            isEnabled = true
            backgroundTintList = colorStateList
        }
    }

    private fun openSettingDialog() {
        val appPreferences = AppPreferences(this)



        customSettingPopupLayoutBinding = CustomSettingPopupLayoutBinding.inflate(layoutInflater)
        customSettinDialog = Dialog(this)
        customSettinDialog?.setContentView(customSettingPopupLayoutBinding.root)

        val displayMetrics = resources.displayMetrics
        val width = (displayMetrics.widthPixels * 0.95).toInt()
        val height = WindowManager.LayoutParams.WRAP_CONTENT
        settingDialog?.window?.setLayout(width, height)

        customSettingPopupLayoutBinding.scSaveRecentAndMessages.isChecked =
            appPreferences.getSaveRecentAndMessages()
        customSettingPopupLayoutBinding.scMergePrefix.isChecked = appPreferences.getMergePrefix()
        customSettingPopupLayoutBinding.scRemoveLeadingZeros.isChecked =
            appPreferences.getRemoveLeadingZeros()
        customSettingPopupLayoutBinding.scHideTheHelpTextFromMain.isChecked =
            appPreferences.getHideHelpTextFromMain()
        customSettingPopupLayoutBinding.scRestorePrefix.isChecked =
            appPreferences.getRestorePrefix()
        customSettingPopupLayoutBinding.scOpenAutomaticallyWhenAppClose.isChecked =
            appPreferences.getOpenAutomaticallyWhenAppClose()


        if (!appPreferences.getRestorePrefix()) {
            val originalString = getString(R.string.restore_prefix_none_when_the_app_closes)
            var yourNumber = "[" + customDialogBinding.ccp?.selectedCountryCodeWithPlus + "]"
            val modifiedString = String.format(originalString, yourNumber)
            customSettingPopupLayoutBinding.restorePrefixNoneWhenTheAppCloses.text =
                modifiedString
        } else {
            val originalString = getString(R.string.restore_prefix_none_when_the_app_closes)
            var yourNumber = "[" + appPreferences.getCountryCode() + "]"
            val modifiedString = String.format(originalString, yourNumber)
            customSettingPopupLayoutBinding.restorePrefixNoneWhenTheAppCloses.text =
                modifiedString
        }


        // Set click listeners for Switches to save the preferences
        customSettingPopupLayoutBinding.scSaveRecentAndMessages.setOnCheckedChangeListener { _, isChecked ->
            appPreferences.setSaveRecentAndMessages(isChecked)
        }

        customSettingPopupLayoutBinding.scMergePrefix.setOnCheckedChangeListener { _, isChecked ->
            appPreferences.setMergePrefix(isChecked)
        }

        customSettingPopupLayoutBinding.scRemoveLeadingZeros.setOnCheckedChangeListener { _, isChecked ->
            appPreferences.setRemoveLeadingZeros(isChecked)
        }

        customSettingPopupLayoutBinding.scHideTheHelpTextFromMain.setOnCheckedChangeListener { _, isChecked ->
            appPreferences.setHideHelpTextFromMain(isChecked)

            if (appPreferences.getHideHelpTextFromMain()) {
                customDialogBinding.conHelpText.visibility = View.GONE
                customDialogBinding.llMainMessageLayout.visibility = View.GONE
            } else {
                customDialogBinding.conHelpText.visibility = View.VISIBLE
                customDialogBinding.llMainMessageLayout.visibility = View.VISIBLE
            }

        }

        customSettingPopupLayoutBinding.scRestorePrefix.setOnCheckedChangeListener { _, isChecked ->

            appPreferences.setRestorePrefix(isChecked)

            if (!isChecked) {
                val originalString = getString(R.string.restore_prefix_none_when_the_app_closes)
                val countryCode = customDialogBinding.ccp?.selectedCountryCodeWithPlus
                val yourNumber = "[$countryCode]"
                val modifiedString = String.format(originalString, yourNumber)
                customSettingPopupLayoutBinding.restorePrefixNoneWhenTheAppCloses.text =
                    modifiedString
            }
        }

        customSettingPopupLayoutBinding.scOpenAutomaticallyWhenAppClose.setOnCheckedChangeListener { _, isChecked ->
            appPreferences.setOpenAutomaticallyWhenAppClose(isChecked)
        }

        customSettingPopupLayoutBinding.tvDeleteAppData.setOnClickListener {
            openDeleteDialog()
        }

        customSettinDialog?.show()
    }


    private lateinit var allContactsAdapter: PhoneNumberAdapter
    private lateinit var pinnedContactsAdapter: PhoneNumberAdapter

    private fun openSaveNumberDialog() {
        val appPreferences = AppPreferences(this)

        val savedContectListPopupLayoutBinding =
            SavedContectListPopupLayoutBinding.inflate(layoutInflater)
        customSaveContetctDialog = Dialog(this)
        customSaveContetctDialog?.setContentView(savedContectListPopupLayoutBinding.root)

        val displayMetrics = resources.displayMetrics
        val width = (displayMetrics.widthPixels * 0.9).toInt()
        val height = WindowManager.LayoutParams.WRAP_CONTENT

        customSaveContetctDialog?.window?.setLayout(width, height)

        val allPhoneNumbers = appPreferences.getPhoneNumbers().toList()
        val pinnedPhoneNumbers = appPreferences.getPinnedPhoneNumbers().toList()

        // Adapter for all contacts
        allContactsAdapter = PhoneNumberAdapter(this, false, allPhoneNumbers,
            onPinClickListener = { phoneNumber ->
                appPreferences.addPinnedPhoneNumber(phoneNumber)
                appPreferences.removePhoneNumber(phoneNumber)
                updateAdapterData()
            },
            onUnpinClickListener = { phoneNumber ->
                appPreferences.removePinnedPhoneNumber(phoneNumber)
                updateAdapterData()
            },
            onDeleteClickListener = { phoneNumber ->
                appPreferences.removePhoneNumber(phoneNumber)
                updateAdapterData()
            },
            onItemClickListener = { phoneNumber ->
                openDialogForItem(phoneNumber)
            })

        // Adapter for pinned contacts
        pinnedContactsAdapter = PhoneNumberAdapter(this, true, pinnedPhoneNumbers,
            onPinClickListener = { phoneNumber ->
                appPreferences.addPinnedPhoneNumber(phoneNumber)
                updateAdapterData()
            },
            onUnpinClickListener = { phoneNumber ->
                appPreferences.removePinnedPhoneNumber(phoneNumber)
                appPreferences.addPhoneNumber(phoneNumber)
                updateAdapterData()
            },
            onDeleteClickListener = { phoneNumber ->
                appPreferences.removePinnedPhoneNumber(phoneNumber)
                updateAdapterData()
            },
            onItemClickListener = { phoneNumber ->
                openDialogForItem(phoneNumber)
            })

        savedContectListPopupLayoutBinding.recyclerViewAllContacts.layoutManager =
            GridLayoutManager(this, 1)
        savedContectListPopupLayoutBinding.recyclerViewAllContacts.adapter = allContactsAdapter

        savedContectListPopupLayoutBinding.recyclerViewPinnedContacts.layoutManager =
            GridLayoutManager(this, 1)
        savedContectListPopupLayoutBinding.recyclerViewPinnedContacts.adapter =
            pinnedContactsAdapter

        savedContectListPopupLayoutBinding.closeButton.setOnClickListener {
            customSaveContetctDialog?.dismiss()
        }

        customSaveContetctDialog?.show()
    }

    private fun updateAdapterData() {
        val appPreferences = AppPreferences(this)
        allContactsAdapter.updateData(appPreferences.getPhoneNumbers().toList())
        pinnedContactsAdapter.updateData(appPreferences.getPinnedPhoneNumbers().toList())
    }

    private fun openDialogForItem(phoneNumber: String) {

        val phoneNumberWithCountryCode = phoneNumber

        val phoneNumberUtil = PhoneNumberUtil.getInstance()
        try {
            val phoneNumber = phoneNumberUtil.parse(phoneNumberWithCountryCode, null)
            val countryCode = phoneNumber.countryCode
            val nationalNumber = phoneNumber.nationalNumber

            customDialogBinding.ccp.setCountryForPhoneCode(countryCode)
            if (!nationalNumber.equals(null)) {
                customDialogBinding.editTextCarrierNumber.setText(nationalNumber.toString())
            }
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }

        customSaveContetctDialog?.dismiss()
    }


    private fun openDeleteDialog() {
        deleteDilogPopupLayoutBinding = DeleteDilogPopupLayoutBinding.inflate(layoutInflater)
        deleteDialog = Dialog(this)
        deleteDialog?.setContentView(deleteDilogPopupLayoutBinding.root)


        val appPreferences = AppPreferences(this)
        deleteDilogPopupLayoutBinding.tvOk.setOnClickListener {

            appPreferences.clearAllData()
            customSettinDialog?.dismiss()
            customDialog?.dismiss()
            customSaveContetctDialog?.dismiss()
            settingDialog?.dismiss()
            deleteDialog?.dismiss()
            customSettinDialog?.dismiss()
//            disableShortcut(this, "whatsappShortcutId")
//            removeShortcut1(this, "yourShortcutId")
            finish()

        }

        deleteDilogPopupLayoutBinding.tvCancel.setOnClickListener {
            deleteDialog?.dismiss()
        }


        deleteDialog?.show()
    }

    private fun disableShortcut(context: Context, shortcutId: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val shortcutManager = context.getSystemService(ShortcutManager::class.java)

            if (shortcutManager?.isRequestPinShortcutSupported == true) {
                shortcutManager.disableShortcuts(listOf(shortcutId))
                Log.d("ShortcutManager", "Shortcut disabled: $shortcutId")
            } else {
                Log.e("ShortcutManager", "RequestPinShortcut is not supported.")
            }
        } else {
            Log.e("ShortcutManager", "Android version is below Oreo (API level 26).")
        }
    }

    private fun removeShortcut1(context: Context, shortcutId: String) {
        val shortcutIntent = Intent("com.android.launcher.action.UNINSTALL_SHORTCUT")
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortcutId)
        shortcutIntent.putExtra(
            Intent.EXTRA_SHORTCUT_INTENT,
            Intent(context, TransparentActivity::class.java)
        )
        context.sendBroadcast(shortcutIntent)
    }


    private fun openWhatsAppChat(phoneNumber: String) {
        try {
            val uri = Uri.parse("https://wa.me/$phoneNumber")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun openWhatsAppChatWithChat(phoneNumber: String, message: String? = null) {
        try {
            val shortcutManager = getSystemService(ShortcutManager::class.java)

            if (shortcutManager.isRequestPinShortcutSupported) {
                // Form the URI with the phone number and optional message
                val uri = Uri.parse(
                    "https://api.whatsapp.com/send?phone=$phoneNumber" +
                            if (!message.isNullOrBlank()) "&text=${
                                URLEncoder.encode(
                                    message,
                                    "UTF-8"
                                )
                            }" else ""
                )

                // Create an intent with the URI
                val intent = Intent(Intent.ACTION_VIEW, uri)

                // Build the shortcut info
                val shortcutInfo = ShortcutInfo.Builder(this, "whatsappShortcutId")
                    .setShortLabel("WhatsApp Chat")
                    .setLongLabel("Open WhatsApp Chat")
                    .setIcon(Icon.createWithResource(this, R.mipmap.ic_launcher))
                    .setIntent(intent)
                    .build()

                // Request to pin the shortcut
                shortcutManager.requestPinShortcut(shortcutInfo, null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun makePhoneCall(phoneNumber: String) {
        try {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun shareLink(phoneNumber: String) {
        val uri = Uri.parse("https://wa.me/$phoneNumber")
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, uri.toString())
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun openSmsWithMessage(phoneNumber: String, message: String) {
        try {
            val uri = Uri.parse("smsto:$phoneNumber")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.putExtra("sms_body", message)
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createWhatsAppChatShortcut(phoneNumber: String) {

        Log.i("TAG", "Formatted phone number: " + phoneNumber)
        try {
            val shortcutManager = getSystemService(ShortcutManager::class.java)

            if (shortcutManager.isRequestPinShortcutSupported) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://wa.me/$phoneNumber")

                val shortcutInfo = ShortcutInfo.Builder(this, "whatsappShortcutId$phoneNumber")
                    .setShortLabel(phoneNumber)
                    .setLongLabel("Open WhatsApp Chat")
                    .setIcon(Icon.createWithResource(this, R.mipmap.ic_launcher))
                    .setIntent(intent)
                    .build()

                shortcutManager.requestPinShortcut(shortcutInfo, null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createWhatsAppChatWithMessageShortcut(
        phoneNumber: String,
        message: String? = null,
    ) {
        try {
            val shortcutManager = getSystemService(ShortcutManager::class.java)

            if (shortcutManager.isRequestPinShortcutSupported) {
                // Form the URI with the phone number and optional message
                val uri = Uri.parse(
                    "https://wa.me/$phoneNumber" + if (!message.isNullOrBlank()) "?text=${
                        URLEncoder.encode(
                            message,
                            "UTF-8"
                        )
                    }" else ""
                )

                // Create an intent with the URI
                val intent = Intent(Intent.ACTION_VIEW, uri)

                // Build the shortcut info
                val shortcutInfo = ShortcutInfo.Builder(this, "whatsappShortcutId$phoneNumber")
                    .setShortLabel(phoneNumber)
                    .setLongLabel("Open WhatsApp Chat")
                    .setIcon(Icon.createWithResource(this, R.mipmap.ic_launcher))
                    .setIntent(intent)
                    .build()

                // Request to pin the shortcut
                shortcutManager.requestPinShortcut(shortcutInfo, null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getLastCopiedText(): String? {
        val appPreferences = AppPreferences(this)
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?

        clipboardManager?.let { manager ->
            if (manager.hasPrimaryClip()) {
                val clip = manager.primaryClip
                if (clip != null && clip.itemCount > 0) {
                    val lastCopiedItem = clip.getItemAt(clip.itemCount - 1)
                    val copiedText = lastCopiedItem.text?.toString()

                    if (copiedText != null) {
                        if (appPreferences.getRemoveLeadingZeros()) {
                            val cleanedText = copiedText.removeLeadingZeros()
                            if (isNumeric(cleanedText)) {
                                return cleanedText
                            } else {
                                showToast("Copied text is not a number")
                            }
                        } else {
                            if (isNumeric(copiedText)) {
                                return copiedText
                            } else {
                                showToast("Copied text is not a number")
                            }
                        }
                    }
                }
            }
        }

        return null
    }

    private fun isNumeric(text: String): Boolean {
        return text.matches("-?\\d+(\\.\\d+)?".toRegex())
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    override fun onPause() {
        super.onPause()
        customDialog?.dismiss()
    }

    override fun onDestroy() {
        val appPreferences = AppPreferences(this)
        val countryCode = customDialogBinding.ccp?.selectedCountryCodeWithPlus
        appPreferences.setCountryCode(countryCode)
        super.onDestroy()
    }

    private fun isDarkTheme(): Boolean {
        val currentNightMode =
            resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES
    }

    private fun getFormattedPhoneNumbe1r(): String {
        val countryCode = customDialogBinding.ccp?.selectedCountryCodeWithPlus
        val carrierNumber = customDialogBinding.editTextCarrierNumber?.text.toString()
        return "$countryCode$carrierNumber"
    }

    private fun getFormattedPhoneNumber(): String {
        val countryCode = customDialogBinding.ccp?.selectedCountryCodeWithPlus
        val carrierNumber = customDialogBinding.editTextCarrierNumber?.text.toString()
        val phoneNumber = "$countryCode$carrierNumber"
        Log.i("TAG", "Formatted phone number: $phoneNumber")
        return phoneNumber
    }

    fun String.removeLeadingZeros(removeZeros: Boolean = true): String {
        return if (removeZeros) {
            this.replaceFirst("^0+".toRegex(), "")
        } else {
            this
        }
    }


    private fun removeShortcut(context: Context, shortcutId: String) {
        val intent = Intent("com.android.launcher.action.UNINSTALL_SHORTCUT")
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortcutId)
        intent.putExtra("duplicate", false)  // Required for some launchers
        intent.putExtra(
            Intent.EXTRA_SHORTCUT_INTENT,
            Intent(context, TransparentActivity::class.java)
        )

        context.sendBroadcast(intent)

        Log.d("ShortcutManager", "Shortcut removed: $shortcutId")
    }

    private fun setMaxScrollHeight(scrollView: ScrollView, maxHeight: Int) {
        val params = scrollView.layoutParams
        params.height = maxHeight
        scrollView.layoutParams = params
    }

}
