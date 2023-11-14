package com.bignerdranch.android.criminalintent

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.view.doOnLayout
import androidx.fragment.app.DialogFragment
import com.bignerdranch.android.criminalintent.databinding.FragmentPhotoZoomDialogBinding
import java.io.File

class PhotoZoomFragment: DialogFragment() {

    private var _binding: FragmentPhotoZoomDialogBinding? = null
    private val binding get() = _binding!!


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentPhotoZoomDialogBinding.inflate(layoutInflater)

        val photoFile: File = arguments?.getSerializable("photo_file") as File

        if (photoFile?.exists()) {
            binding.photoZoomImageView.doOnLayout { measuredView ->
                val width = if (measuredView.width > 0) measuredView.width else DEFAULT_WIDTH
                val height = if (measuredView.height > 0) measuredView.height else DEFAULT_HEIGHT
                val scaledBitmap = getScaledBitmap(photoFile.path, width, height)
                binding.photoZoomImageView.setImageBitmap(scaledBitmap)
            }
        } else {
            binding.photoZoomImageView.setImageBitmap(null)
        }

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val DEFAULT_WIDTH = 600
        private const val DEFAULT_HEIGHT = 400

        fun newInstance(photoFile: File): PhotoZoomFragment {
            val args = Bundle().apply {
                putSerializable("photo_file", photoFile)
            }
            return PhotoZoomFragment().apply {
                arguments = args
            }
        }
    }
}