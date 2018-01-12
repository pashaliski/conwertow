[#assign linkHref = ""]
[#assign linkText = ""]

[#if item[field + 'internal']?has_content]
    [#assign contentPage = cmsfn.nodeById(item[field + 'internal']!"")!""]
    [#if contentPage?has_content]
    [#--[#if !cmsfn.asJCRNode(contentPage).hasProperty("mgnl:deleted")]--]
        [#assign linkHref = cmsfn.link(contentPage)!]
        [#assign linkText = item['title' + field]!item['title' + field?cap_first]!contentPage.title!i18n['link.label']!]
    [#--[/#if]--]

    [#-- load page properties --]

        [#if !title?has_content]
            [#assign title = contentPage.title!]
        [/#if]
        [#if !abstract?has_content]
            [#assign abstract = contentPage.abstractList!]
        [/#if]
        [#if !abstract?has_content]
            [#assign abstract = contentPage.abstract!]
        [/#if]
        [#if !image?has_content]
            [#assign image = contentPage.imageList!]
        [/#if]
        [#if !image?has_content]
            [#assign image = contentPage.image!]
        [/#if]
        [#if !imageMobile?has_content]
            [#assign imageMobile = contentPage.imageListMobile!]
        [/#if]
        [#if !imageMobile?has_content]
            [#assign imageMobile = contentPage.imageMobile!]
        [/#if]
        [#if !imageTablet?has_content]
            [#assign imageTablet = contentPage.imageListTablet!]
        [/#if]
        [#if !imageTablet?has_content]
            [#assign imageTablet = contentPage.imageTablet!]
        [/#if]
    [/#if]
[/#if]
