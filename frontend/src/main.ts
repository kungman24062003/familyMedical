// src/main.ts
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

// Vuetify – BẮT BUỘC phải có 5 dòng này
import 'vuetify/styles'
import '@mdi/font/css/materialdesignicons.css'

import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'   // ← THIẾU DÒNG NÀY
import * as directives from 'vuetify/directives'   // ← VÀ DÒNG NÀY
import { aliases, mdi } from 'vuetify/iconsets/mdi'

const vuetify = createVuetify({
  components,    // ← phải truyền vào đây
  directives,    // ← và đây
  icons: {
    defaultSet: 'mdi',
    aliases,
    sets: { mdi },
  },
})

createApp(App)
  .use(vuetify)
  .use(router)
  .mount('#app')