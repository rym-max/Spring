/**
 * Create with WebStorm
 * Author: Daxiu Huang
 * CreateTime: 2017/9/6 10:21
 */
//分页组件
var pageComponent = Vue.extend({
    template: `<div class="row">
            <div class="col-lg-5">{{recordinfo}}</div>
            <div class="col-lg-7">
                <nav aria-label="Page navigation" style="text-align: right;">
                <ul class="pagination">
                    <li :class="{\'disabled\':curPage==1}">
                        <a href="javascript:;" @click="goPage(curPage==1?1:curPage-1)" aria-label="Previous">
                            <span aria-hidden="true">上一页</span>
                        </a>
                    </li>
                    <li v-for="page in showPageBtn" :class="{\'active\':page==curPage}">
                        <a href="javascript:;" v-if="page>0" @click="goPage(page)">{{page}}</a>
                        <a href="javascript:;" v-else>···</a>
                    </li>
                    <li :class="{\'disabled\':curPage==pages}">
                        <a href="javascript:;" @click="goPage(curPage==pages?pages:curPage+1)" aria-label="Next">
                            <span aria-hidden="true">下一页</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>`,
    props: {
        total: {
            type: Number,
            default: 0
        },
        page: {
            type: Number,
            default: 1
        },
        rows: {
            type: Number,
            default: 10
        }
    },
    data() {
        return {
            curPage: this.page,
            pages: Math.ceil(this.total / this.rows),
            recordinfo: "显示 " + ((this.page - 1) * this.rows + 1) + " 到 " + (this.page * this.rows) + " 共 " + this.total + " 条记录",
        }
    },
    computed: {
        showPageBtn() {
            //let pageNum = this.pages;
            //let index = this.curPage;
            //let arr = [];
            //if (pageNum <= 10) {
            //    for (let i = 1; i <= pageNum; i++) {
            //        arr.push(i)
            //    }
            //    return arr
            //}
            //if (index <= 2) return [1, 2, 3, 0, pageNum];
            //if (index >= pageNum - 1) return [1, 0, pageNum - 7, pageNum - 1, pageNum];
            //if (index === 3) return [1, 2, 3, 4, 0, pageNum];
            //if (index === pageNum - 2) return [1, 0, pageNum - 3, pageNum - 2, pageNum - 1, pageNum];
            //return [1, 0, index - 1, index, index + 1, 0, pageNum];
            var left = 1
            var right = this.pages
            var ar = []
            if (this.pages >= 11) {
                if (this.curPage > 5 && this.curPage < this.pages - 4) {
                    left = this.curPage - 5
                    right = this.curPage + 4
                } else {
                    if (this.curPage <= 5) {
                        left = 1
                        right = 10
                    } else {
                        right = this.pages
                        left = this.pages - 9
                    }
                }
            }
            while (left <= right) {
                ar.push(left)
                left++
            }
            if (ar[0] > 1) {
                ar[0] = 1;
                ar[1] = -1;
            }
            if (ar[ar.length - 1] < this.pages) {
                ar[ar.length - 1] = this.pages;
                ar[ar.length - 2] = 0;
            }
            return ar
        },
    },
    methods: {
        goPage(page) {
            if (page != this.curPage) {
                this.curPage = page;
                this.$emit('topage', this.curPage);
                this.recordinfo = "显示 " + ((this.curPage - 1) * this.rows + 1) + " 到 " + (this.curPage * this.rows) + " 共 " + this.total + " 条记录";
            } else {
                console.log('Already in the current page');
            }
        }
    }
});
Vue.component('pagination', pageComponent);