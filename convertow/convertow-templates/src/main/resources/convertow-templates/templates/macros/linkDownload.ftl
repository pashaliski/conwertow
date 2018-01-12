[#include "/asg-templates/templates/functions/assetFunctions.ftl"]

[#assign linkHref = ""]
[#assign linkText = ""]
[#assign linkTarget = ""]

[#assign downloadLink = ctx.assetId!item[field + 'download']!""]
[#if downloadLink?has_content]
    [#assign lang = cmsfn.language()!""]
    [#assign asset = damfn.getAsset(downloadLink)!""]
    [#if asset?has_content]
        [#assign identifier = asset.getItemKey().getAssetId()!""]
        [#assign assetNode = cmsfn.nodeById(identifier,"dam")!""]
        [#assign linkHref = damfn.getAssetLink(downloadLink)!""]
        [#assign linkTarget = " target=\"_blank\" "]
    [/#if]
[/#if]