package be.mygod.vpnhotspot.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import be.mygod.vpnhotspot.R

private val Mint = Color(0xFF35EDC5)
private val Deep = Color(0xFF061116)
private val Panel = Color(0xFF0E2228)
private val Inactive = Color(0xFF6E858A)

@Composable
fun TetheringDashboard(
    hotspotRunning: Boolean,
    forwardingInterfaceCount: Int,
    detectedInterfaceCount: Int,
    connectedDeviceCount: Int = 0,
    onConfigureHotspot: () -> Unit,
    onManageTethering: () -> Unit,
    onOpenClients: () -> Unit = {},
) {
    val activeColor = if (hotspotRunning) Mint else Inactive
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Surface(shape = RoundedCornerShape(28.dp), color = Deep) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        stringResource(R.string.ui_v2_app_title),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = if (forwardingInterfaceCount > 0) Mint.copy(alpha = 0.14f) else Color.White.copy(alpha = 0.08f),
                    ) {
                        Text(
                            if (forwardingInterfaceCount > 0) stringResource(R.string.ui_v2_vpn_active)
                            else stringResource(R.string.ui_v2_vpn_idle),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                            color = if (forwardingInterfaceCount > 0) Mint else Color(0xFFB2C5C7),
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                }
                Box(contentAlignment = Alignment.Center, modifier = Modifier.size(192.dp)) {
                    Canvas(modifier = Modifier.size(192.dp)) {
                        val mid = Offset(size.width / 2f, size.height / 2f)
                        val radius = size.minDimension * 0.40f
                        drawCircle(
                            brush = Brush.radialGradient(
                                listOf(activeColor.copy(alpha = 0.19f), activeColor.copy(alpha = 0.025f), Color.Transparent),
                                center = mid,
                                radius = radius * 1.36f,
                            ),
                            radius = radius * 1.36f,
                            center = mid,
                        )
                        drawCircle(
                            color = activeColor.copy(alpha = 0.22f),
                            radius = radius + 11.dp.toPx(),
                            center = mid,
                            style = Stroke(width = 2.dp.toPx()),
                        )
                        drawCircle(
                            brush = Brush.sweepGradient(
                                listOf(activeColor.copy(alpha = 0.35f), activeColor, activeColor.copy(alpha = 0.4f), activeColor),
                                center = mid,
                            ),
                            radius = radius,
                            center = mid,
                            style = Stroke(width = 7.dp.toPx(), cap = StrokeCap.Round),
                        )
                        drawCircle(
                            color = Color(0xFF10242A),
                            radius = radius - 10.dp.toPx(),
                            center = mid,
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(R.drawable.ic_wifi_tethering),
                            contentDescription = null,
                            tint = activeColor,
                            modifier = Modifier.size(51.dp),
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            stringResource(if (hotspotRunning) R.string.ui_v2_hotspot_on else R.string.ui_v2_hotspot_off),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (hotspotRunning) Mint else Color.White,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
                Text(
                    stringResource(if (hotspotRunning) R.string.ui_v2_status_on else R.string.ui_v2_status_off),
                    color = Color(0xFF9EB7BC),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                )
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    StatusMetric(
                        modifier = Modifier.weight(1f),
                        value = connectedDeviceCount.toString(),
                        title = stringResource(R.string.ui_v2_devices),
                        tint = Mint,
                    )
                    StatusMetric(
                        modifier = Modifier.weight(1f),
                        value = forwardingInterfaceCount.toString(),
                        title = stringResource(R.string.ui_v2_forwarded),
                        tint = Color(0xFF8BB5FF),
                    )
                }
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = Panel,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        Surface(shape = CircleShape, color = Mint.copy(alpha = 0.15f)) {
                            Box(modifier = Modifier.size(42.dp), contentAlignment = Alignment.Center) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_wifi_tethering),
                                    contentDescription = null,
                                    tint = Mint,
                                    modifier = Modifier.size(24.dp),
                                )
                            }
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                stringResource(R.string.ui_v2_share_vpn),
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White,
                            )
                            Text(
                                stringResource(
                                    if (forwardingInterfaceCount > 0) R.string.ui_v2_forwarding_on
                                    else R.string.ui_v2_forwarding_off
                                ),
                                color = Color(0xFFA2B7BB),
                                style = MaterialTheme.typography.bodySmall,
                            )
                        }
                    }
                }
                Button(
                    onClick = onManageTethering,
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Mint, contentColor = Deep),
                    shape = RoundedCornerShape(24.dp),
                ) {
                    Text(stringResource(R.string.ui_v2_manage), fontWeight = FontWeight.Bold)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedButton(
                        onClick = onConfigureHotspot,
                        modifier = Modifier.weight(1f).height(46.dp),
                        shape = RoundedCornerShape(20.dp),
                    ) {
                        Text(stringResource(R.string.ui_v2_wifi_settings), maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                    OutlinedButton(
                        onClick = onOpenClients,
                        modifier = Modifier.weight(1f).height(46.dp),
                        shape = RoundedCornerShape(20.dp),
                    ) {
                        Text(stringResource(R.string.ui_v2_clients), maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Box(
                modifier = Modifier.weight(1f).background(Panel, RoundedCornerShape(20.dp)).padding(15.dp),
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text(stringResource(R.string.ui_v2_detected), color = Color(0xFF9CB8BC), style = MaterialTheme.typography.labelMedium)
                    Text(detectedInterfaceCount.toString(), color = Color.White, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                }
            }
            Box(
                modifier = Modifier.weight(1f).background(Panel, RoundedCornerShape(20.dp)).padding(15.dp),
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text(stringResource(R.string.ui_v2_security), color = Color(0xFF9CB8BC), style = MaterialTheme.typography.labelMedium)
                    Text(stringResource(if (forwardingInterfaceCount > 0) R.string.ui_v2_active else R.string.ui_v2_inactive), color = Mint, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun StatusMetric(modifier: Modifier, value: String, title: String, tint: Color) {
    Surface(modifier = modifier, color = Panel, shape = RoundedCornerShape(18.dp)) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Text(value, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = tint)
            Text(title, style = MaterialTheme.typography.labelMedium, color = Color(0xFFC1D2D4))
        }
    }
}
