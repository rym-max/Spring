<%--
  Created by IntelliJ IDEA.
  User: starcloud
  Date: 19/11/14
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:_FrontLayout>
<jsp:attribute name="scripts">

</jsp:attribute>
<jsp:body>
<!--主体内容开始-->
<div class="content clr" :controller="app">
    <!--当前位置-->
    <div class="locat">
        您现在所在的位置：<a class="" href="../index.jsp">网站首页</a> > <a class="current" href="">期刊列表</a>
    </div>
    <!--专题列表-->
    <div class="content_boxM mt10 wit_bg clr">
        <div class="search-result-list search_list search_list_02">
            <dl>
                <dt style="position: relative;">
                    <a class="toe" target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=92440B&from=journal_journalsearch&kind=1">欧洲研究</a>
                    <tt class="i_fx s-share">
                        <img src="/Content/images/icon_fx.png" alt="" width="14" height="14">
                        <em class="s-share-list">
                            <a target="_blank" href=""><i class="icon-s4"></i></a>
                            <a target="_blank" href=""><i class="icon-s6"></i></a>
                            <a target="_blank" href=""><i class="icon-s2"></i></a>
                            <a target="_blank" href=""><i class="icon-s3"></i></a>
                            <a target="_blank" href=""><i class="icon-s1"></i></a>
                            <a target="_blank" href=""><i class="icon-s5"></i></a>
                        </em>
                    </tt>
                </dt>
                <dd class="paper-preview permissinimg">
                    <img alt="" width="120" height="160" src="Content/images/journal/1.jpg"><!--if-->
                </dd>
                <dd>
                    <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：2486</strong></span>
                </dd>
                <dd>
                    <a target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=92440B&from=journal_journalsearch&kind=1">有关欧洲问题研究的学术性刊物。刊登我国学者对欧洲各国及欧共体的政治、经济、国际关系、社会文化等方面的研究论文，介绍国外学术界的研究成果，提供国际问题背景资料，报道学术动态。</a>
                </dd>
                <dd class="more_dd mt10">
                    <a target="_blank" class="more_yellow " href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=92440B&from=journal_journalsearch&kind=1">read MORE</a>
                </dd>
            </dl>
            <dl>
                <dt style="position: relative;">
                    <a class="toe" target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=82454X&from=journal_journalsearch&kind=1">德国研究</a>
                    <tt class="i_fx s-share">
                        <img src="/Content/images/icon_fx.png" alt="" width="14" height="14">
                        <em class="s-share-list">
                            <a target="_blank" href=""><i class="icon-s4"></i></a>
                            <a target="_blank" href=""><i class="icon-s6"></i></a>
                            <a target="_blank" href=""><i class="icon-s2"></i></a>
                            <a target="_blank" href=""><i class="icon-s3"></i></a>
                            <a target="_blank" href=""><i class="icon-s1"></i></a>
                            <a target="_blank" href=""><i class="icon-s5"></i></a>
                        </em>
                    </tt>
                </dt>
                <dd class="paper-preview permissinimg">
                    <img alt="" width="120" height="160" src="Content/images/journal/2.jpg"><!--if-->
                </dd>
                <dd>
                    <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：2486</strong></span>
                </dd>
                <dd>
                    <a target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=82454X&from=journal_journalsearch&kind=1">
                        外语学习刊物。以满足我国德语师生、德语工作者的需要，促进中德文化交流为宗旨。主要栏目阅读、词汇、语法、口语、口译、笔译指导、科技、初学园地。
                    </a>
                </dd>
                <dd class="more_dd mt10">
                    <a target="_blank" class="more_yellow " href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=82454X&from=journal_journalsearch&kind=1">read MORE</a>
                </dd>
            </dl>
            <dl>
                <dt style="position: relative;">
                    <a class="toe" target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?kind=1&gch=83393X&from=Qikan_Journal_Detail">法国研究</a>
                    <tt class="i_fx s-share">
                        <img src="/Content/images/icon_fx.png" alt="" width="14" height="14">
                        <em class="s-share-list">
                            <a target="_blank" href=""><i class="icon-s4"></i></a>
                            <a target="_blank" href=""><i class="icon-s6"></i></a>
                            <a target="_blank" href=""><i class="icon-s2"></i></a>
                            <a target="_blank" href=""><i class="icon-s3"></i></a>
                            <a target="_blank" href=""><i class="icon-s1"></i></a>
                            <a target="_blank" href=""><i class="icon-s5"></i></a>
                        </em>
                    </tt>
                </dt>
                <dd class="paper-preview permissinimg">
                    <img alt="" width="120" height="160" src="Content/images/journal/3.jpg"><!--if-->
                </dd>
                <dd>
                    <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：2486</strong></span>
                </dd>
                <dd>
                    <a target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?kind=1&gch=83393X&from=Qikan_Journal_Detail">
                        《法国研究》是中国唯一研究法国问题的学术期刊，依托全国重点综合性大学的优势,邀请文理学科权威学者担任评审专家,研究范围涵盖经济、政治、社会、文化、哲学、文学、语言、翻译等各方面,同时也接受发表译作。
                    </a>
                </dd>
                <dd class="more_dd mt10">
                    <a target="_blank" class="more_yellow " href="http://qikan.cqvip.com/Qikan/Journal/Summary?kind=1&gch=83393X&from=Qikan_Journal_Detail">read MORE</a>
                </dd>
            </dl>
            <dl>
                <dt style="position: relative;">
                    <a class="toe" target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=91406X&from=journal_journalsearch&kind=1">国际论坛</a>
                    <tt class="i_fx s-share">
                        <img src="/Content/images/icon_fx.png" alt="" width="14" height="14">
                        <em class="s-share-list">
                            <a target="_blank" href=""><i class="icon-s4"></i></a>
                            <a target="_blank" href=""><i class="icon-s6"></i></a>
                            <a target="_blank" href=""><i class="icon-s2"></i></a>
                            <a target="_blank" href=""><i class="icon-s3"></i></a>
                            <a target="_blank" href=""><i class="icon-s1"></i></a>
                            <a target="_blank" href=""><i class="icon-s5"></i></a>
                        </em>
                    </tt>
                </dt>
                <dd class="paper-preview permissinimg">
                    <img alt="" width="120" height="160" src="Content/images/journal/4.jpg"><!--if-->
                </dd>
                <dd>
                    <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：2486</strong></span>
                </dd>
                <dd>
                    <a target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=91406X&from=journal_journalsearch&kind=1">
                        本刊是综合性国际问题研究学术刊物。辟有国际政治、国际关系、中外关系、世界经济理论、时事评论、探讨国别与地区、外著编译等栏目。读者对象为人事国际问研究和外事工作人员以及大专院样相关专业的师生等。
                    </a>
                </dd>
                <dd class="more_dd mt10">
                    <a target="_blank" class="more_yellow " href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=91406X&from=journal_journalsearch&kind=1">read MORE</a>
                </dd>
            </dl>
            <dl>
                <dt style="position: relative;">
                    <a class="toe" target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=92442X&from=journal_journalsearch&kind=1">世界经济与政治</a>
                    <tt class="i_fx s-share">
                        <img src="/Content/images/icon_fx.png" alt="" width="14" height="14">
                        <em class="s-share-list">
                            <a target="_blank" href=""><i class="icon-s4"></i></a>
                            <a target="_blank" href=""><i class="icon-s6"></i></a>
                            <a target="_blank" href=""><i class="icon-s2"></i></a>
                            <a target="_blank" href=""><i class="icon-s3"></i></a>
                            <a target="_blank" href=""><i class="icon-s1"></i></a>
                            <a target="_blank" href=""><i class="icon-s5"></i></a>
                        </em>
                    </tt>
                </dt>
                <dd class="paper-preview permissinimg">
                    <img alt="" width="120" height="160" src="Content/images/journal/5.jpg"><!--if-->
                </dd>
                <dd>
                    <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：2486</strong></span>
                </dd>
                <dd>
                    <a target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=92442X&from=journal_journalsearch&kind=1">
                        《世界经济与政治》由中国社会科学院世界经济与政治研究所主办，是一份具有广泛影响并深受读者欢迎的综合性国际关系学术刊物，2012年入选国家社科基金首批资助名单，2014年获得中国出版政府奖期刊提名奖，在国内主要期刊评价体系中，均名列前茅。本刊坚持正确的思想导向，以"理论性、战略性、综合性和现实性"为办刊宗旨，设有国际关系理论、世界政治、国际战略、中国外交和国际政治经济学等特色栏目。
                    </a>
                </dd>
                <dd class="more_dd mt10">
                    <a target="_blank" class="more_yellow " href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=92442X&from=journal_journalsearch&kind=1">read MORE</a>
                </dd>
            </dl>
            <dl>
                <dt style="position: relative;">
                    <a class="toe" target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=94094X&from=journal_journalsearch&kind=1">国际问题研究</a>
                    <tt class="i_fx s-share">
                        <img src="/Content/images/icon_fx.png" alt="" width="14" height="14">
                        <em class="s-share-list">
                            <a target="_blank" href=""><i class="icon-s4"></i></a>
                            <a target="_blank" href=""><i class="icon-s6"></i></a>
                            <a target="_blank" href=""><i class="icon-s2"></i></a>
                            <a target="_blank" href=""><i class="icon-s3"></i></a>
                            <a target="_blank" href=""><i class="icon-s1"></i></a>
                            <a target="_blank" href=""><i class="icon-s5"></i></a>
                        </em>
                    </tt>
                </dt>
                <dd class="paper-preview permissinimg">
                    <img alt="" width="120" height="160" src="Content/images/journal/6.jpg"><!--if-->
                </dd>
                <dd>
                    <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：2486</strong></span>
                </dd>
                <dd>
                    <a target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=94094X&from=journal_journalsearch&kind=1">
                        国际问题研究学术性刊物。宣传我国的外交路线和政策,交流国际问题研究成果。主要刊登有关国际政治和国际关系方面的文章。读者对象为国际问题研究工作者、外事工作者、高等院校师生和对国际问题有兴趣的读者。有英文目次。
                    </a>
                </dd>
                <dd class="more_dd mt10">
                    <a target="_blank" class="more_yellow " href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=94094X&from=journal_journalsearch&kind=1">read MORE</a>
                </dd>
            </dl>
            <dl>
                <dt style="position: relative;">
                    <a class="toe" target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=81437X&from=journal_journalsearch&kind=1">现代国际关系</a>
                    <tt class="i_fx s-share">
                        <img src="/Content/images/icon_fx.png" alt="" width="14" height="14">
                        <em class="s-share-list">
                            <a target="_blank" href=""><i class="icon-s4"></i></a>
                            <a target="_blank" href=""><i class="icon-s6"></i></a>
                            <a target="_blank" href=""><i class="icon-s2"></i></a>
                            <a target="_blank" href=""><i class="icon-s3"></i></a>
                            <a target="_blank" href=""><i class="icon-s1"></i></a>
                            <a target="_blank" href=""><i class="icon-s5"></i></a>
                        </em>
                    </tt>
                </dt>
                <dd class="paper-preview permissinimg">
                    <img alt="" width="120" height="160" src="Content/images/journal/7.jpg"><!--if-->
                </dd>
                <dd>
                    <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：2486</strong></span>
                </dd>
                <dd>
                    <a target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=81437X&from=journal_journalsearch&kind=1">
                        本刊为国际问题研究学术性刊物。分析世界局势和国际关系，研究世界各地区和国家的政治、经济问题。
                    </a>
                </dd>
                <dd class="more_dd mt10">
                    <a target="_blank" class="more_yellow " href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=81437X&from=journal_journalsearch&kind=1">read MORE</a>
                </dd>
            </dl>
            <dl>
                <dt style="position: relative;">
                    <a class="toe" target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=90401A&from=journal_journalsearch&kind=1">外交评论：外交学院学报</a>
                    <tt class="i_fx s-share">
                        <img src="/Content/images/icon_fx.png" alt="" width="14" height="14">
                        <em class="s-share-list">
                            <a target="_blank" href=""><i class="icon-s4"></i></a>
                            <a target="_blank" href=""><i class="icon-s6"></i></a>
                            <a target="_blank" href=""><i class="icon-s2"></i></a>
                            <a target="_blank" href=""><i class="icon-s3"></i></a>
                            <a target="_blank" href=""><i class="icon-s1"></i></a>
                            <a target="_blank" href=""><i class="icon-s5"></i></a>
                        </em>
                    </tt>
                </dt>
                <dd class="paper-preview permissinimg">
                    <img alt="" width="120" height="160" src="Content/images/journal/8.jpg"><!--if-->
                </dd>
                <dd>
                    <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：2486</strong></span>
                </dd>
                <dd>
                    <a target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=90401A&from=journal_journalsearch&kind=1">
                        本刊设有中国外交、国际关系、外交学、外交政策研究、外交工作与回忆、国际法、世界经济、学术动态等栏目，主要刊登国际政治、中国外交、外交学、世界经济等学科的科研成果以及外交工作回忆、国外调研报告、有价值的国外著作译文等。学报创刊以来，始终坚持正确的政治导向，认真贯彻双百方针，努力办出“外交”特色。在组织和发表有关国际关系、世界经济、国际法，中国外交等方面高质量的学术论文的同时，积极邀请外事部门的负责人、资深外交官撰写中国外交理论、思想和外交实践、外交风格的论文、回忆录等，颇受读者的欢迎和好评。
                    </a>
                </dd>
                <dd class="more_dd mt10">
                    <a target="_blank" class="more_yellow " href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=90401A&from=journal_journalsearch&kind=1">read MORE</a>
                </dd>
            </dl>
            <dl>
                <dt style="position: relative;">
                    <a class="toe" target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=95355X&from=journal_journalsearch&kind=1">国际展望</a>
                    <tt class="i_fx s-share">
                        <img src="/Content/images/icon_fx.png" alt="" width="14" height="14">
                        <em class="s-share-list">
                            <a target="_blank" href=""><i class="icon-s4"></i></a>
                            <a target="_blank" href=""><i class="icon-s6"></i></a>
                            <a target="_blank" href=""><i class="icon-s2"></i></a>
                            <a target="_blank" href=""><i class="icon-s3"></i></a>
                            <a target="_blank" href=""><i class="icon-s1"></i></a>
                            <a target="_blank" href=""><i class="icon-s5"></i></a>
                        </em>
                    </tt>
                </dt>
                <dd class="paper-preview permissinimg">
                    <img alt="" width="120" height="160" src="Content/images/journal/9.jpg"><!--if-->
                </dd>
                <dd>
                    <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：2486</strong></span>
                </dd>
                <dd>
                    <a target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=95355X&from=journal_journalsearch&kind=1">
                        综合性国际时事理论刊物。本刊融知识性、新闻性、理论性、真实性和可读性于一体，为读者提供重大国际事件的综合分析和背景材料，提供世界改革的最新动态和可资借鉴的经验。
                    </a>
                </dd>
                <dd class="more_dd mt10">
                    <a target="_blank" class="more_yellow " href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=95355X&from=journal_journalsearch&kind=1">read MORE</a>
                </dd>
            </dl>
            <dl>
                <dt style="position: relative;">
                    <a class="toe" target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=96476A&from=journal_journalsearch&kind=1">世界政治与经济论坛</a>
                    <img src="/Content/images/icon_fx.png" alt="" width="14" height="14">
                    <em class="s-share-list">
                        <a target="_blank" href=""><i class="icon-s4"></i></a>
                        <a target="_blank" href=""><i class="icon-s6"></i></a>
                        <a target="_blank" href=""><i class="icon-s2"></i></a>
                        <a target="_blank" href=""><i class="icon-s3"></i></a>
                        <a target="_blank" href=""><i class="icon-s1"></i></a>
                        <a target="_blank" href=""><i class="icon-s5"></i></a>
                    </em>
                    </tt>
                </dt>
                <dd class="paper-preview permissinimg">
                    <img alt="" width="120" height="160" src="Content/images/journal/10.jpg"><!--if-->
                </dd>
                <dd>
                    <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：2486</strong></span>
                </dd>
                <dd>
                    <a target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=96476A&from=journal_journalsearch&kind=1">
                        内容以研究世界经济与国际政治为主，反映世界经济和国际政治领域的最新成果，追踪国际热点问题。
                    </a>
                </dd>
                <dd class="more_dd mt10">
                    <a target="_blank" class="more_yellow " href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=96476A&from=journal_journalsearch&kind=1">read MORE</a>
                </dd>
            </dl>
            <dl>
                <dt style="position: relative;">
                    <a class="toe" target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=82189X&from=journal_journalsearch&kind=1">国际政治研究</a>
                    <tt class="i_fx s-share">
                        <img src="/Content/images/icon_fx.png" alt="" width="14" height="14">
                        <em class="s-share-list">
                            <a target="_blank" href=""><i class="icon-s4"></i></a>
                            <a target="_blank" href=""><i class="icon-s6"></i></a>
                            <a target="_blank" href=""><i class="icon-s2"></i></a>
                            <a target="_blank" href=""><i class="icon-s3"></i></a>
                            <a target="_blank" href=""><i class="icon-s1"></i></a>
                            <a target="_blank" href=""><i class="icon-s5"></i></a>
                        </em>
                    </tt>
                </dt>
                <dd class="paper-preview permissinimg">
                    <img alt="" width="120" height="160" src="Content/images/journal/11.jpg"><!--if-->
                </dd>
                <dd>
                    <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：2486</strong></span>
                </dd>
                <dd>
                    <a target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=82189X&from=journal_journalsearch&kind=1">
                        本刊自1980年创刊（内部发行）以来，《国际政治研究》刊发了学院内外大量高水平、高质量的国际问题研究成果，其思想和学术影响日渐扩大，成为国内从事国际政治、国际关系教学和研究的重要学术园地。
                    </a>
                </dd>
                <dd class="more_dd mt10">
                    <a target="_blank" class="more_yellow " href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=82189X&from=journal_journalsearch&kind=1">read MORE</a>
                </dd>
            </dl>
            <dl>
                <dt style="position: relative;">
                    <a class="toe" target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=81866X&from=journal_journalsearch&kind=1">欧洲研究</a>
                    <tt class="i_fx s-share">
                        <img src="/Content/images/icon_fx.png" alt="" width="14" height="14">
                        <em class="s-share-list">
                            <a target="_blank" href=""><i class="icon-s4"></i></a>
                            <a target="_blank" href=""><i class="icon-s6"></i></a>
                            <a target="_blank" href=""><i class="icon-s2"></i></a>
                            <a target="_blank" href=""><i class="icon-s3"></i></a>
                            <a target="_blank" href=""><i class="icon-s1"></i></a>
                            <a target="_blank" href=""><i class="icon-s5"></i></a>
                        </em>
                    </tt>
                </dt>
                <dd class="paper-preview permissinimg">
                    <img alt="" width="120" height="160" src="Content/images/journal/12.jpg"><!--if-->
                </dd>
                <dd>
                    <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：2486</strong></span>
                </dd>
                <dd>
                    <a target="_blank" href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=81866X&from=journal_journalsearch&kind=1">
                        本刊由中共中央编译局和中国国际共运史学会主办，中央级学术刊物，国际政治类、社会主义研究类、国际共运类全国中文核心期刊。本刊宗旨是追踪国内外最新理论动态，探讨世界社会主义现实命运，分析当代资本主义发展趋势，报道中国改革开放新理论、新政策、新举措、新成果。
                    </a>
                </dd>
                <dd class="more_dd mt10">
                    <a target="_blank" class="more_yellow " href="http://qikan.cqvip.com/Qikan/Journal/Summary?gch=81866X&from=journal_journalsearch&kind=1">read MORE</a>
                </dd>
            </dl>
            <dl>
                <dt style="position: relative;">
                    <a class="toe" target="_blank" href="https://link.springer.com/journal/volumesAndIssues/12399">Zeitschrift für Außen- und Sicherheitspolitik</a>
                    <tt class="i_fx s-share">
                        <img src="/Content/images/icon_fx.png" alt="" width="14" height="14">
                        <em class="s-share-list">
                            <a target="_blank" href=""><i class="icon-s4"></i></a>
                            <a target="_blank" href=""><i class="icon-s6"></i></a>
                            <a target="_blank" href=""><i class="icon-s2"></i></a>
                            <a target="_blank" href=""><i class="icon-s3"></i></a>
                            <a target="_blank" href=""><i class="icon-s1"></i></a>
                            <a target="_blank" href=""><i class="icon-s5"></i></a>
                        </em>
                    </tt>
                </dt>
                <dd class="paper-preview permissinimg">
                    <img alt="" width="120" height="160" src="Content/images/journal/14.jpg"><!--if-->
                </dd>
                <dd>
                    <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：2486</strong></span>
                </dd>
                <dd>
                    <a target="_blank" href="https://link.springer.com/journal/volumesAndIssues/12399">
                        Die Zeitschrift für Außen- und Sicherheitspolitik (ZfAS) ist die neue Zeitschrift für theoriegeleitete und empirisch gehaltvolle Außenpolitikanalysen, sicherheitspolitische Studien und Analysen der internationalen Politik. Sie bietet ein Forum, in dem Entwicklungen der internationalen Beziehungen und ihre Effekte auf außen- und sicherheitspolitische Prozesse ebenso diskutiert werden wie die Wirkungen außen- und sicherheitspol …
                    </a>
                </dd>
                <dd class="more_dd mt10">
                    <a target="_blank" class="more_yellow " href="https://link.springer.com/journal/volumesAndIssues/12399">read MORE</a>
                </dd>
            </dl>
            <dl>
                <dt style="position: relative;">
                    <a class="toe" target="_blank" href="https://www.zib.nomos.de/archiv/">Zeitschrift für Internationale Beziehungen</a>
                    <tt class="i_fx s-share">
                        <img src="/Content/images/icon_fx.png" alt="" width="14" height="14">
                        <em class="s-share-list">
                            <a target="_blank" href=""><i class="icon-s4"></i></a>
                            <a target="_blank" href=""><i class="icon-s6"></i></a>
                            <a target="_blank" href=""><i class="icon-s2"></i></a>
                            <a target="_blank" href=""><i class="icon-s3"></i></a>
                            <a target="_blank" href=""><i class="icon-s1"></i></a>
                            <a target="_blank" href=""><i class="icon-s5"></i></a>
                        </em>
                    </tt>
                </dt>
                <dd class="paper-preview permissinimg">
                    <img alt="" width="120" height="160" src="Content/images/journal/15.jpg"><!--if-->
                </dd>
                <dd>
                    <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：2486</strong></span>
                </dd>
                <dd>
                    <a target="_blank" href="https://www.zib.nomos.de/archiv/">
                        Die Zeitschrift für Internationale Beziehungen (zib) ist die führende deutschsprachige Zeitschrift, die die gesamte Breite der Internationalen Beziehungen abdeckt. Sie veröffentlicht Originalmanuskripte, die sich in origineller und innovativer Weise mit substanziellen Fragen der internationalen Politik auseinandersetzen.
                    </a>
                </dd>
                <dd class="more_dd mt10">
                    <a target="_blank" class="more_yellow " href="https://www.zib.nomos.de/archiv/">read MORE</a>
                </dd>
            </dl>
            <dl>
                <dt style="position: relative;">
                    <a class="toe" target="_blank" href="http://www.chinapolitik.de/">Chinapolitik</a>
                    <tt class="i_fx s-share">
                        <img src="/Content/images/icon_fx.png" alt="" width="14" height="14">
                        <em class="s-share-list">
                            <a target="_blank" href=""><i class="icon-s4"></i></a>
                            <a target="_blank" href=""><i class="icon-s6"></i></a>
                            <a target="_blank" href=""><i class="icon-s2"></i></a>
                            <a target="_blank" href=""><i class="icon-s3"></i></a>
                            <a target="_blank" href=""><i class="icon-s1"></i></a>
                            <a target="_blank" href=""><i class="icon-s5"></i></a>
                        </em>
                    </tt>
                </dt>
                <dd class="paper-preview permissinimg">
                    <img alt="" width="120" height="160" src="Content/images/journal/16.png"><!--if-->
                </dd>
                <dd>
                    <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：2486</strong></span>
                </dd>
                <dd>
                    <a target="_blank" href="http://www.chinapolitik.de/">
                        China Analysis leistet innovative Beiträge zur sozialwissenschaftlichen China-Forschung und macht die neuen Erkenntnisse zugleich Praktikern aus Politik, Wirtschaft und Medien zugänglich.
                    </a>
                </dd>
                <dd class="more_dd mt10">
                    <a target="_blank" class="more_yellow " href="http://www.chinapolitik.de/">read MORE</a>
                </dd>
            </dl>
            <dl>
                <dt style="position: relative;">
                    <a class="toe" target="_blank" href="https://onlinelibrary.wiley.com/journal/14685965">Journal of Common Market Studies</a>
                    <tt class="i_fx s-share">
                        <img src="/Content/images/icon_fx.png" alt="" width="14" height="14">
                        <em class="s-share-list">
                            <a target="_blank" href=""><i class="icon-s4"></i></a>
                            <a target="_blank" href=""><i class="icon-s6"></i></a>
                            <a target="_blank" href=""><i class="icon-s2"></i></a>
                            <a target="_blank" href=""><i class="icon-s3"></i></a>
                            <a target="_blank" href=""><i class="icon-s1"></i></a>
                            <a target="_blank" href=""><i class="icon-s5"></i></a>
                        </em>
                    </tt>
                </dt>
                <dd class="paper-preview permissinimg">
                    <img alt="" width="120" height="160" src="Content/images/journal/17.png"><!--if-->
                </dd>
                <dd>
                    <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：2486</strong></span>
                </dd>
                <dd>
                    <a target="_blank" href="https://onlinelibrary.wiley.com/journal/14685965">
                        JCMS: Journal of Common Market Studies publishes innovative peer-reviewed research on Europe and comparative regional studies. It is motivated to disseminate original, significant and rigorous contributions to the field. JCMS is a multidisciplinary outlet that welcomes a plurality of methodological and theoretical approaches within the social sciences especially, international relations, politics, political economy, economics, law and sociology.
                    </a>
                </dd>
                <dd class="more_dd mt10">
                    <a target="_blank" class="more_yellow " href="https://onlinelibrary.wiley.com/journal/14685965">read MORE</a>
                </dd>
            </dl>
            <dl>
                <dt style="position: relative;">
                    <a class="toe" target="_blank" href="https://www.tandfonline.com/toc/fgrp20/current">German Politics</a>
                    <tt class="i_fx s-share">
                        <img src="/Content/images/icon_fx.png" alt="" width="14" height="14">
                        <em class="s-share-list">
                            <a target="_blank" href=""><i class="icon-s4"></i></a>
                            <a target="_blank" href=""><i class="icon-s6"></i></a>
                            <a target="_blank" href=""><i class="icon-s2"></i></a>
                            <a target="_blank" href=""><i class="icon-s3"></i></a>
                            <a target="_blank" href=""><i class="icon-s1"></i></a>
                            <a target="_blank" href=""><i class="icon-s5"></i></a>
                        </em>
                    </tt>
                </dt>
                <dd class="paper-preview permissinimg">
                    <img alt="" width="120" height="160" src="Content/images/journal/18.jpg"><!--if-->
                </dd>
                <dd>
                    <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：2486</strong></span>
                </dd>
                <dd>
                    <a target="_blank" href="https://www.tandfonline.com/toc/fgrp20/current">
                        Since its launch in 1992, German Politics has established itself as the leading international journal in its field. Its mission is to provide theoretically informed perspectives on the changing agendas of German Politics . It engages with themes that connect Germany comparatively with other states – the challenges of globalisation, changes in international relations, and the widening and deepening of the European Union. It also links work on Germany to wider debates and issues in comparative politics, public policy, political behaviour, and political theory.
                    </a>
                </dd>
                <dd class="more_dd mt10">
                    <a target="_blank" class="more_yellow " href="https://www.tandfonline.com/toc/fgrp20/current">read MORE</a>
                </dd>
            </dl>
            <dl>
                <dt style="position: relative;">
                    <a class="toe" target="_blank" href="https://www.berghahnjournals.com/view/journals/gps/gps-overview.xml">German Politics and Society</a>
                    <tt class="i_fx s-share">
                        <img src="/Content/images/icon_fx.png" alt="" width="14" height="14">
                        <em class="s-share-list">
                            <a target="_blank" href=""><i class="icon-s4"></i></a>
                            <a target="_blank" href=""><i class="icon-s6"></i></a>
                            <a target="_blank" href=""><i class="icon-s2"></i></a>
                            <a target="_blank" href=""><i class="icon-s3"></i></a>
                            <a target="_blank" href=""><i class="icon-s1"></i></a>
                            <a target="_blank" href=""><i class="icon-s5"></i></a>
                        </em>
                    </tt>
                </dt>
                <dd class="paper-preview permissinimg">
                    <img alt="" width="120" height="160" src="Content/images/journal/19.jpg"><!--if-->
                </dd>
                <dd>
                    <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：2486</strong></span>
                </dd>
                <dd>
                    <a target="_blank" href="https://www.berghahnjournals.com/view/journals/gps/gps-overview.xml">
                        German Politics and Society is a peer-reviewed journal published and distributed by Berghahn Journals. It is the only American publication that explores issues in modern Germany from the combined perspectives of the social sciences, history, and cultural studies.
                        The journal provides a forum for critical analysis and debate about politics, history, film, literature, visual arts, and popular culture in contemporary Germany. Every issue includes contributions by renowned scholars commenting on recent books about Germany.
                    </a>
                </dd>
                <dd class="more_dd mt10">
                    <a target="_blank" class="more_yellow " href="https://www.berghahnjournals.com/view/journals/gps/gps-overview.xml">read MORE</a>
                </dd>
            </dl>
            <dl>
                <dt style="position: relative;">
                    <a class="toe" target="_blank" href="https://www.tandfonline.com/toc/fwep20/current">West European Politics</a>
                    <tt class="i_fx s-share">
                        <img src="/Content/images/icon_fx.png" alt="" width="14" height="14">
                        <em class="s-share-list">
                            <a target="_blank" href=""><i class="icon-s4"></i></a>
                            <a target="_blank" href=""><i class="icon-s6"></i></a>
                            <a target="_blank" href=""><i class="icon-s2"></i></a>
                            <a target="_blank" href=""><i class="icon-s3"></i></a>
                            <a target="_blank" href=""><i class="icon-s1"></i></a>
                            <a target="_blank" href=""><i class="icon-s5"></i></a>
                        </em>
                    </tt>
                </dt>
                <dd class="paper-preview permissinimg">
                    <img alt="" width="120" height="160" src="Content/images/journal/20.jpg"><!--if-->
                </dd>
                <dd>
                    <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：2486</strong></span>
                </dd>
                <dd>
                    <a target="_blank" href="https://www.tandfonline.com/toc/fwep20/current">
                        West European Politics ( WEP ) has established itself as one of the most authoritative journals covering politics, government and public policy in Western Europe.
                        Its comprehensive scope, embracing the major political developments, including the European Union, and its coverage of all national elections in Western Europe, make it essential reading for both academics and practitioners. The journal normally publishes at least two special issues per volume.
                    </a>
                </dd>
                <dd class="more_dd mt10">
                    <a target="_blank" class="more_yellow " href="https://www.tandfonline.com/toc/fwep20/current">read MORE</a>
                </dd>
            </dl>
            <dl>
                <dt style="position: relative;">
                    <a class="toe" target="_blank" href="https://www.tandfonline.com/toc/rjpp20/current">Journal of European Public Policy（JEEP）
                    </a>
                    <tt class="i_fx s-share">
                        <img src="/Content/images/icon_fx.png" alt="" width="14" height="14">
                        <em class="s-share-list">
                            <a target="_blank" href=""><i class="icon-s4"></i></a>
                            <a target="_blank" href=""><i class="icon-s6"></i></a>
                            <a target="_blank" href=""><i class="icon-s2"></i></a>
                            <a target="_blank" href=""><i class="icon-s3"></i></a>
                            <a target="_blank" href=""><i class="icon-s1"></i></a>
                            <a target="_blank" href=""><i class="icon-s5"></i></a>
                        </em>
                    </tt>
                </dt>
                <dd class="paper-preview permissinimg">
                    <img alt="" width="120" height="160" src="Content/images/journal/22.jpg"><!--if-->
                </dd>
                <dd>
                    <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：2486</strong></span>
                </dd>
                <dd>
                    <a target="_blank" href="https://www.tandfonline.com/toc/rjpp20/current">
                        The Journal of European Public Policy (JEPP) has established itself as one of the flagship journals in the study of public policy, European politics and the EU and aims to provide a comprehensive and definitive source of analytical, theoretical and methodological articles in these fields. Focusing on the dynamics of public policy in Europe, the journal encourages a wide range of social science approaches, both qualitative and quantitative.
                    </a>
                </dd>
                <dd class="more_dd mt10">
                    <a target="_blank" class="more_yellow " href="https://www.tandfonline.com/toc/rjpp20/current">read MORE</a>
                </dd>
            </dl>
            <dl>
                <dt style="position: relative;">
                    <a class="toe" target="_blank" href="https://www.tandfonline.com/toc/geui20/current">Journal of European Integration（JEI）</a>
                    <tt class="i_fx s-share">
                        <img src="/Content/images/icon_fx.png" alt="" width="14" height="14">
                        <em class="s-share-list">
                            <a target="_blank" href=""><i class="icon-s4"></i></a>
                            <a target="_blank" href=""><i class="icon-s6"></i></a>
                            <a target="_blank" href=""><i class="icon-s2"></i></a>
                            <a target="_blank" href=""><i class="icon-s3"></i></a>
                            <a target="_blank" href=""><i class="icon-s1"></i></a>
                            <a target="_blank" href=""><i class="icon-s5"></i></a>
                        </em>
                    </tt>
                </dt>
                <dd class="paper-preview permissinimg">
                    <img alt="" width="120" height="160" src="Content/images/journal/23.jpg"><!--if-->
                </dd>
                <dd>
                    <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：2486</strong></span>
                </dd>
                <dd>
                    <a target="_blank" href="https://www.tandfonline.com/toc/geui20/current">
                        The Journal of European Integration publishes scholarly work on all aspects of the European integration process. We welcome submissions from a variety of disciplinary or multidisciplinary perspectives, ranging from political science and political economy to public administration, law, history, sociology and cultural studies. However, the primary emphasis is on the discussion of European integration in the fields of political science and international relations.
                    </a>
                </dd>
                <dd class="more_dd mt10">
                    <a target="_blank" class="more_yellow " href="https://www.tandfonline.com/toc/geui20/current">read MORE</a>
                </dd>
            </dl>
            <dl>
                <dt style="position: relative;">
                    <a class="toe" target="_blank" href="http://journals.sagepub.com/home/eup">European Union Politics（EUP）</a>
                    <tt class="i_fx s-share">
                        <img src="/Content/images/icon_fx.png" alt="" width="14" height="14">
                        <em class="s-share-list">
                            <a target="_blank" href=""><i class="icon-s4"></i></a>
                            <a target="_blank" href=""><i class="icon-s6"></i></a>
                            <a target="_blank" href=""><i class="icon-s2"></i></a>
                            <a target="_blank" href=""><i class="icon-s3"></i></a>
                            <a target="_blank" href=""><i class="icon-s1"></i></a>
                            <a target="_blank" href=""><i class="icon-s5"></i></a>
                        </em>
                    </tt>
                </dt>
                <dd class="paper-preview permissinimg">
                    <img alt="" width="120" height="160" src="Content/images/journal/24.png"><!--if-->
                </dd>
                <dd>
                    <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：2486</strong></span>
                </dd>
                <dd>
                    <a target="_blank" href="http://journals.sagepub.com/home/eup">
                        European Union Politics is an international academic journal for advanced peer reviewed research and scholarship on all aspects of the process of government, politics and policy in the European Union. EUP aims to stimulate debate and provide a forum to bridge the theoretical and empirical analysis on the political unification of Europe.
                    </a>
                </dd>
                <dd class="more_dd mt10">
                    <a target="_blank" class="more_yellow " href="http://journals.sagepub.com/home/eup">read MORE</a>
                </dd>
            </dl>
            <dl>
                <dt style="position: relative;">
                    <a class="toe" target="_blank" href="https://www.cambridge.org/core/journals/china-quarterly">The China Quarterly</a>
                    <tt class="i_fx s-share">
                        <img src="/Content/images/icon_fx.png" alt="" width="14" height="14">
                        <em class="s-share-list">
                            <a target="_blank" href=""><i class="icon-s4"></i></a>
                            <a target="_blank" href=""><i class="icon-s6"></i></a>
                            <a target="_blank" href=""><i class="icon-s2"></i></a>
                            <a target="_blank" href=""><i class="icon-s3"></i></a>
                            <a target="_blank" href=""><i class="icon-s1"></i></a>
                            <a target="_blank" href=""><i class="icon-s5"></i></a>
                        </em>
                    </tt>
                </dt>
                <dd class="paper-preview permissinimg">
                    <img alt="" width="120" height="160" src="Content/images/journal/31.jpg"><!--if-->
                </dd>
                <dd>
                    <span class="works pr10"><b class="type-ico-id"></b><strong>作品数：2486</strong></span>
                </dd>
                <dd>
                    <a target="_blank" href="https://www.cambridge.org/core/journals/china-quarterly">
                        The China Quarterly is the leading scholarly journal in its field, covering all aspects of contemporary China including Taiwan. Its interdisciplinary approach covers a range of subjects including anthropology/sociology, literature and the arts, business/economics, geography, history, international affairs, law, and politics.
                    </a>
                </dd>
                <dd class="more_dd mt10">
                    <a target="_blank" class="more_yellow " href="https://www.cambridge.org/core/journals/china-quarterly">read MORE</a>
                </dd>
            </dl>

        </div>
        <!--翻页部分开始-->
        <!--<ul class="pagination" id="pagination"></ul>-->
    </div>
</div>
</jsp:body>
</t:_FrontLayout>