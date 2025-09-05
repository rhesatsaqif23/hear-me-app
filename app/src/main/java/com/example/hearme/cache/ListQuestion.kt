package com.example.hearme.cache

import com.example.hearme.data.model.QuestionSectionData

object ListQuestion {
    val questions = listOf(
        QuestionSectionData(
            title = "Pembukaan & Pencairan Suasana",
            questions = listOf(
                "Bisa ceritakan sedikit tentang bagaimana hari Anda sejauh ini?",
                "Apa hal pertama yang terlintas di pikiran Anda ketika mendengar kata “tenang” atau “damai”?"
            )
        ),
        QuestionSectionData(
            title = "Aktivitas & Rutinitas",
            questions = listOf(
                "Bagaimana biasanya Anda memulai hari Anda?",
                "Seberapa sibuk hari-hari Anda dalam seminggu terakhir?",
                "Adakah aktivitas yang menurut Anda paling melelahkan akhir-akhir ini?"
            )
        ),
        QuestionSectionData(
            title = "Emosi & Perasaan",
            questions = listOf(
                "Dalam seminggu terakhir, emosi apa yang paling sering Anda rasakan?",
                "Apa hal terakhir yang membuat Anda merasa cemas atau tegang?",
                "Bagaimana tubuh Anda biasanya bereaksi saat merasa tertekan (misalnya sulit tidur, sakit kepala, atau lainnya)?"
            )
        ),
        QuestionSectionData(
            title = "Dukungan Sosial",
            questions = listOf(
                "Jika sedang menghadapi masalah, biasanya kepada siapa Anda bercerita?",
                "Menurut Anda, apakah dukungan dari orang-orang di sekitar sudah cukup membantu?"
            )
        ),
        QuestionSectionData(
            title = "Koping & Relaksasi",
            questions = listOf(
                "Apa yang biasanya Anda lakukan untuk menenangkan diri ketika stres?",
                "Apakah ada kegiatan yang membuat Anda merasa lebih lega atau bahagia akhir-akhir ini?"
            )
        ),
        QuestionSectionData(
            title = "Refleksi & Penutup",
            questions = listOf(
                "Kalau harus menggambarkan kondisi Anda sekarang dengan satu kata, kata apa yang Anda pilih?",
                "Apa harapan Anda untuk diri sendiri dalam beberapa minggu ke depan?"
            )
        )
    )
}